package com.example.storemanagement;

import com.example.storemanagement.entities.Client;
import com.example.storemanagement.entities.Product;
import com.example.storemanagement.entities.Role;
import com.example.storemanagement.model.ClientDto;
import com.example.storemanagement.model.ProductDto;
import com.example.storemanagement.model.StoreDto;
import com.example.storemanagement.repository.ClientRepository;
import com.example.storemanagement.repository.RoleRepository;
import com.example.storemanagement.service.ClientService;
import com.example.storemanagement.service.ProductService;
import com.example.storemanagement.service.RoleService;
import com.example.storemanagement.service.StoreService;
import com.google.common.collect.Lists;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class StoreManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder,
                                        ClientRepository clientRepository,
                                        ProductService productService,
                                        StoreService storeService,
                                        RoleRepository roleRepository) {
        return args -> {

            List<Client> clients = Lists.newArrayList();

            Role roleUser = Role.builder()
                    .roleName("USER")
                    .build();

            Role roleAdmin = Role.builder()
                    .roleName("ADMIN")
                    .build();

            //add roles
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);

            Client admin = Client.builder()
                    .email("karim@gmail.com")
                    .phone("0708080808")
                    .firstName("AITOUBOUHOU")
                    .lastName("KARIM")
                    .password(passwordEncoder.encode("1234"))
                    .username("karim")
                    .status("ACTIVE")
                    .roles(Set.of(roleAdmin))
                    .build();

            Client user = Client.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("AITOUBOUHOU")
                    .lastName("HAFID")
                    .password(passwordEncoder.encode("1234"))
                    .username("hafid")
                    .status("ACTIVE")
                    .roles(Set.of(roleUser))
                    .build();

            clients.add(user);
            clients.add(admin);

            clientRepository.saveAll(clients);

            for (int i = 0; i < 5; i++) {
                List<Client> list = Lists.newArrayList();
                 Client client1 = Client.builder()
                        .email("test@gmail.com")
                        .phone("0708080808")
                        .firstName("TEST")
                        .lastName("TEST")
                         .password(passwordEncoder.encode("test"))
                        .username("test")
                         .status("ACTIVE")
                        .roles(Set.of(roleAdmin))
                        .build();

                Client client2 = Client.builder()
                        .email("xxx@gmail.com")
                        .phone("212700998877")
                        .firstName("XXX")
                        .lastName("XXX")
                        .password(passwordEncoder.encode("user"))
                        .username("user")
                        .status("ACTIVE")
                        .roles(Set.of(roleUser))
                        .build();

                list.add(client1);
                list.add(client2);

                clientRepository.saveAll(list);
            }


            //add store
            StoreDto store1 = StoreDto.builder()
                    .quantity(200)
                    .emplacement("floor1")
                    .build();


            for (int i = 0; i < 18; i++) {
                ProductDto productDto = ProductDto.builder()
                        .title("iphone XR")
                        .company("apple")
                        .price(10000L)
                        .currency("MAD")
                        .build();

                Product product = productService.addProduct(productDto);
                storeService.save(store1, product);
            }


        };
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
