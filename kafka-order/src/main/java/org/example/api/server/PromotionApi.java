package org.example.api.server;

import org.example.api.request.PromotionRequest;
import org.example.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promotion")
public class PromotionApi {
    @Autowired
    private PromotionService promotionService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPromotion(@RequestBody PromotionRequest request) {
        System.out.println(request);
        promotionService.createPromotion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request.getPromotionCode());
    }
}
