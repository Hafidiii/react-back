package com.example.storemanagement;

import com.example.storemanagement.entities.Product;
import com.example.storemanagement.model.ClientDto;
import com.example.storemanagement.model.ProductDto;
import com.example.storemanagement.model.RoleDto;
import com.example.storemanagement.model.StoreDto;
import com.example.storemanagement.service.ClientService;
import com.example.storemanagement.service.ProductService;
import com.example.storemanagement.service.RoleService;
import com.example.storemanagement.service.StoreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
public class StoreManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ClientService clientService,
                                        ProductService productService,
                                        StoreService storeService,
                                        RoleService roleService) {
        return args -> {

            //add roles
            roleService.addRoles(Arrays.asList("ADMIN", "USER"));

            RoleDto user = RoleDto.builder()
                    .roleName("USER")
                    .build();

            RoleDto admin = RoleDto.builder()
                    .roleName("ADMIN")
                    .build();

            ClientDto client1 = ClientDto.builder()
                    .email("karim@gmail.com")
                    .phone("0708080808")
                    .firstName("aitoubouhou")
                    .lastName("abdelkrim")
                    .password("1234")
                    .username("karim")
                    .roleDto(Set.of(admin))
                    .build();


            ClientDto client2 = ClientDto.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("aitoubouhou")
                    .lastName("Hafid")
                    .password("1234")
                    .roleDto(Set.of(user))
                    .username("hafid")
                    .build();


            ProductDto productDto1 = ProductDto.builder()
                    .title("iphone XR")
                    .company("apple")
                    .price(10000L)
                    .currency("MAD")
                    .build();

            ProductDto productDto2 = ProductDto.builder()
                    .title("iphone 12pro")
                    .company("apple")
                    .price(15000L)
                    .currency("MAD")
                    .build();

            ProductDto productDto3 = ProductDto.builder()
                    .title("iphone 14pro max")
                    .company("apple")
                    .price(35000L)
                    .currency("MAD")
                    .build();



            //add users
            clientService.signup(client1);
            clientService.signup(client2);

            //add Products
            Product product1 = productService.addProduct(productDto1);
            Product product2 = productService.addProduct(productDto2);
            Product product3 = productService.addProduct(productDto3);

            //add store
            StoreDto store1 = StoreDto.builder()
                    .quantity(200)
                    .emplacement("floor1")
                    .build();

            StoreDto store2 = StoreDto.builder()
                    .quantity(100)
                    .emplacement("floor2")
                    .build();

            StoreDto store3 = StoreDto.builder()
                    .quantity(1000)
                    .emplacement("floor3")
                    .build();

            storeService.save(store1, product1);
            storeService.save(store2, product2);
            storeService.save(store3, product3);

        };
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
