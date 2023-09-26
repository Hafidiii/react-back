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

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(ProductDto productdto) {
        Map<String, Object> map = new HashMap<>();

        Product product = Product.builder()
                .title(productdto.getTitle())
                .company(productdto.getCompany())
                .currency(productdto.getCurrency())
                .price(productdto.getPrice())
                .build();
        return productRepository.save(product);
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
