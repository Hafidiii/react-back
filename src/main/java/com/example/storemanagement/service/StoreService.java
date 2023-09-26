package com.example.storemanagement.service;

import com.example.storemanagement.entities.Product;
import com.example.storemanagement.model.StoreDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface StoreService {
    Map<String, Object> getAllStores();

    Map<String, Object> save(StoreDto storeDto, Product product);

    Map<String, Object> editQuantity(Long id, int quantity);
}
