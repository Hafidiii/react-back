package com.example.storemanagement;

import com.example.storemanagement.model.ClientDto;
import com.example.storemanagement.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StoreManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    //@Bean
    CommandLineRunner runUserAuthentication(ClientService clientService) {
        return args -> {
            //add users


            //add roles

            //add roles to users

        };
    }


    @Bean
    CommandLineRunner commandLineRunner(ClientService clientService) {
        return args -> {
            clientService.signup(new ClientDto(null, "karim", "aitoubouhou", "abdelkrim",
                    "0708080808", "karim@gmail.com", "1234"));

           };


    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
