package com.example.storemanagement.service.serviceImpl;

import com.example.storemanagement.entities.Product;
import com.example.storemanagement.model.ProductDto;
import com.example.storemanagement.repository.ProductRepository;
import com.example.storemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.storemanagement.constants.Constants.MESSAGE;
import static com.example.storemanagement.constants.Constants.SUCCESS;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Map<String, Object> addProduct(ProductDto productdto) {
        Map<String, Object> map = new HashMap<>();
        Product product = productRepository.findById(productdto.getId()).orElse(null);
        if (product != null) {
            map.put(SUCCESS, false);
            map.put(MESSAGE, "this product is already exist");
            return map;
        }

         product = Product.builder()
                .name(productdto.getName())
                .price(productdto.getPrice())
                .quantity(productdto.getQuantity())
                .build();
        productRepository.save(product);
        map.put(SUCCESS, true);
        map.put(MESSAGE, "this product has been saved successfully");
        return map;
    }

    @Override
    public Map<String, Object> updateProduct(ProductDto productdto) {
        Map<String, Object> map = new HashMap<>();
        Product product = productRepository.findById(productdto.getId()).orElse(null);
        if (product == null) {
            map.put(SUCCESS, false);
            map.put(MESSAGE, "Product not found");
            return map;
        }

         product = Product.builder()
                .name(productdto.getName())
                .price(productdto.getPrice())
                .quantity(productdto.getQuantity())
                .build();
        productRepository.save(product);
        map.put(SUCCESS, true);
        map.put(MESSAGE, "Product updated successfully");
        return map;
    }

    @Override
    public Product getProductById(Long id) {
       return productRepository.findById(id).get();
    }


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
