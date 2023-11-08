package org.example.service;

import org.example.action.PromotionAction;
import org.example.api.request.PromotionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    @Autowired
    private PromotionAction action;

    public void createPromotion(PromotionRequest request) {
        action.publishToKafka(request);
    }
}
