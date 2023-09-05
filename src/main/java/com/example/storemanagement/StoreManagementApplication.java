package com.example.storemanagement;

import com.example.storemanagement.model.ClientDto;
import com.example.storemanagement.service.ClientService;
import com.example.storemanagement.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class StoreManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ClientService clientService, RoleService roleService) {
        return args -> {

            //add roles
            roleService.addRoles(Arrays.asList("ADMIN", "USER"));

            ClientDto client1 = ClientDto.builder()
                    .email("karim@gmail.com")
                    .phone("0708080808")
                    .firstName("aitoubouhou")
                    .lastName("abdelkrim")
                    .password("1234")
                    .username("karim")
                    .role("ADMIN")
                    .build();


            ClientDto client2 = ClientDto.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("aitoubouhou")
                    .lastName("Hafid")
                    .password("1234")
                    .role("USER")
                    .username("hafid")
                    .build();


            //add users
            clientService.signup(client1);
            clientService.signup(client2);

        };
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
