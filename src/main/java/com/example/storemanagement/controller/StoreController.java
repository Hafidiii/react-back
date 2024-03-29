package com.example.storemanagement.controller;

import com.example.storemanagement.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/store")
@CrossOrigin(origins = "*")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/all")
    Map<String, Object> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/editQuantity")
    Map<String, Object> editQuantity(@RequestParam Long id, @RequestParam int quantity) {
        return storeService.editQuantity(id, quantity);
    }

}
