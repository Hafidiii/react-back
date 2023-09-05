package com.example.storemanagement.service;

import com.example.storemanagement.entities.Product;
import com.example.storemanagement.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProductService {

     Map<String, Object> addProduct(ProductDto productdto);
      Map<String, Object> updateProduct(ProductDto productdto);
     Product getProductById(Long id);
     List<Product> getAll();
     void deleteProduct(Long id);
     void deleteAll();


}
