package com.example.storemanagement.service.serviceImpl;

import com.example.storemanagement.entities.Client;
import com.example.storemanagement.entities.Roles;
import com.example.storemanagement.service.ClientService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ClientService clientService;

    public UserDetailsServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientService.getUserByUserName(username);
        if(client ==null ) throw new UsernameNotFoundException("User not found");
        UserDetails userDetails = User.withUsername(client.getUsername())
                .password(client.getPassword())
                .roles(client.getRoles().stream().map(Roles::getRoleName).toArray(String[]::new))
                .build();

        return userDetails;
    }
}
