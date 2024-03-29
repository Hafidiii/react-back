package com.example.storemanagement.service;

import com.example.storemanagement.entities.Client;
import com.example.storemanagement.model.ClientDto;
import com.example.storemanagement.model.LoginDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ClientService {
    Client getUserByUserName(String username);

    List<Client> getAllUsers();

    Map<String, Object> authenticate(LoginDto loginDto);

    Map<String, Object> signup(ClientDto clientDto);

    Map<String, Object> update(ClientDto clientDto);

    Map<String, Object> deactivate(Long id);

    Map<String, Object> reactivate(Long id);


}
