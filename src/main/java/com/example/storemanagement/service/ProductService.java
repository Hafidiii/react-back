package com.example.storemanagement.service;

import com.example.storemanagement.entities.Product;
import com.example.storemanagement.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

     Product addProduct(ProductDto productdto);
     Product getProductById(Long id);
     List<Product> getAll();
     void deleteProduct(Long id);
     void deleteAll();


}
