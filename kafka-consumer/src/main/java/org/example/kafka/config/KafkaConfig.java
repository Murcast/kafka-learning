package org.example.kafka.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.messaging.Message;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        var properties = kafkaProperties.buildConsumerProperties();
        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean(name = "foodOrderErrorHandler")
    public ConsumerAwareListenerErrorHandler consumerAwareListenerErrorHandler() {
        return new ConsumerAwareListenerErrorHandler() {
            private static final Logger log = LoggerFactory.getLogger("FoodOrderConsumerAwareListenerErrorHandler");

            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
                log.info("Received to big order. Put error message into elastic. Message : {}, Error : {}",
                        message.getPayload(), exception.getCause().getMessage());
                return null;
            }
        };
    }

    @Bean
    public CommonErrorHandler generalErrorHandler() {
        return new CommonErrorHandler() {
            private static final Logger log = LoggerFactory.getLogger("GeneralErrorHandler");

            @Override
            public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
                log.info("General error handler: Message : {}, Error : {}", record.value(), thrownException.getCause().getMessage());
                return true;
            }
        };
    }

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> consumerFactory,
            CommonErrorHandler generalErrorHandler) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory);
        factory.setCommonErrorHandler(generalErrorHandler);
        return factory;
    }

    @Bean(name = "retryContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> retryContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory);
        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(1000, 3)));
        return factory;
    }

    @Bean(name = "deadContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> deadContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> consumerFactory,
            KafkaTemplate<String, String> template) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory);

        var recoverer = new DeadLetterPublishingRecoverer(template, (record, ex) -> new TopicPartition("t-invoice-dlt", record.partition()));

        factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer, new FixedBackOff(1000, 3)));
        return factory;
    }
}
