package org.example.api.server;

import org.example.api.request.DiscountRequest;
import org.example.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discount")
public class DiscountApi {
    @Autowired
    private DiscountService service;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createDiscount(@RequestBody DiscountRequest request) {
        System.out.println(request);
        service.createDiscount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request.getDiscountCode());
    }
}
