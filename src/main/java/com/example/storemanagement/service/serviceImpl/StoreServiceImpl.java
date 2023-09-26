package com.example.storemanagement.service.serviceImpl;

import com.example.storemanagement.entities.Product;
import com.example.storemanagement.entities.Store;
import com.example.storemanagement.model.StoreDto;
import com.example.storemanagement.repository.StoreRepository;
import com.example.storemanagement.service.StoreService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.storemanagement.constants.Constants.MESSAGE;
import static com.example.storemanagement.constants.Constants.SUCCESS;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Map<String, Object> getAllStores() {
        Map<String, Object> map = Maps.newHashMap();

        List<Store> all = storeRepository.findAll();

        map.put("result", all);
        map.put("totalElements", all.size());
        return map;
    }


    @Override
    public Map<String, Object> save(StoreDto storeDto, Product product) {
        Map<String, Object> map = Maps.newHashMap();

        if (Objects.isNull(product)) {
            map.put(SUCCESS, false);
            map.put(MESSAGE, "operation failed");
            return map;
        }

        Store store = Store.builder()
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .quantity(storeDto.getQuantity())
                .emplacement(storeDto.getEmplacement())
                .product(product)
                .build();

        storeRepository.save(store);

        map.put(SUCCESS, true);
        map.put(MESSAGE, "store has been created");
        return map;
    }

    @Override
    public Map<String, Object> editQuantity(Long id, int quantity) {
        Map<String, Object> map = Maps.newHashMap();

        Store store = storeRepository.findById(id).orElse(null);

        if (Objects.isNull(store)) {
            map.put(SUCCESS, false);
            map.put(MESSAGE, "store id not found");
            return map;
        }

        store.setUpdatedDate(LocalDateTime.now());
        store.setQuantity(quantity);

        storeRepository.save(store);
        map.put(SUCCESS, true);
        map.put(MESSAGE, "store quantity has been changed successfully");

        return map;
    }
}
