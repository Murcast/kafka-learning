package org.example.service;

import org.example.action.DiscountAction;
import org.example.api.request.DiscountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    @Autowired
    private DiscountAction action;

    public void createDiscount(DiscountRequest request) {
        action.publishToKafka(request);
    }
}
