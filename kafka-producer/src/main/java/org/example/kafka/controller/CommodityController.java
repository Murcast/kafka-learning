package org.example.kafka.controller;

import org.example.kafka.entity.Commodity;
import org.example.kafka.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityController {

    @Autowired
    private CommodityService service;

    @GetMapping(value = "/all")
    public List<Commodity> getAllCommodities() {
        return service.getAllCommodities();
    }
}
