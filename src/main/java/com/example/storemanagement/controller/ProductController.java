package com.example.storemanagement.controller;

import com.example.storemanagement.entities.Product;
import com.example.storemanagement.model.ProductDto;
import com.example.storemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product saveProduct(@RequestBody ProductDto product) {
        return productService.addProduct(product);
    }

    @GetMapping("/get")
    public Product getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }


    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/delete")
    public void deleteProductById(@RequestParam Long id) {
        productService.deleteProduct(id);
    }


    @GetMapping("/deleteAllProduct")
    public void deleteAllProduct() {
        productService.deleteAll();
    }
}






