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

@SpringBootApplication
public class StoreManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ClientService clientService, RoleService roleService) {
        return args -> {

            //add roles
            //roleService.addRoles(Arrays.asList("ADMIN", "USER"));

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


            ClientDto client3 = ClientDto.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("aitoubouhou")
                    .lastName("hafid")
                    .password("admin")
                    .role("USER")
                    .username("admin")
                    .build();


            ClientDto client4 = ClientDto.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("aitoubouhou")
                    .lastName("hafid")
                    .password("test")
                    .role("USER")
                    .username("test")
                    .build();


            ClientDto client5 = ClientDto.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("aitoubouhou")
                    .lastName("user")
                    .password("user")
                    .role("USER")
                    .username("user")
                    .build();

            ClientDto client6 = ClientDto.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("aitoubouhou")
                    .lastName("user")
                    .password("user")
                    .role("USER")
                    .username("user")
                    .build();

            ClientDto client7 = ClientDto.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("aitoubouhou")
                    .lastName("user")
                    .password("user")
                    .role("USER")
                    .username("user")
                    .build();

            ClientDto client8 = ClientDto.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("aitoubouhou")
                    .lastName("user")
                    .password("user")
                    .role("USER")
                    .username("user")
                    .build();

            ClientDto client9 = ClientDto.builder()
                    .email("hafid@gmail.com")
                    .phone("212700998877")
                    .firstName("aitoubouhou")
                    .lastName("user")
                    .password("user")
                    .role("USER")
                    .username("user")
                    .build();


            //add users
            clientService.signup(client1);
            clientService.signup(client2);
            clientService.signup(client3);
            clientService.signup(client4);
            clientService.signup(client5);
            clientService.signup(client6);
            clientService.signup(client7);
            clientService.signup(client8);
            clientService.signup(client9);

        };
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
