package com.example.storemanagement.controller;

import com.example.storemanagement.entities.Client;
import com.example.storemanagement.model.ClientDto;
import com.example.storemanagement.model.LoginDto;
import com.example.storemanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/getUserByUserName")
    public Client getUserByUserName(@RequestParam String username) {
        return clientService.getUserByUserName(username);
    }

    @GetMapping("/all")
    public List<Client> getAllUsers() {
        return clientService.getAllUsers();
    }

    @PostMapping("/signup")
    public Map<String, Object> signup(@RequestBody ClientDto clientDto) {
        return clientService.signup(clientDto);

    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody ClientDto clientDto) {
        return clientService.update(clientDto);

    }

    @PostMapping("/login")
    public Map<String, Object> authenticate(@RequestBody LoginDto loginDto) {
        return clientService.authenticate(loginDto);

    }

    @GetMapping("/deactivate/{id}")
    public Map<String, Object> deactivate(@PathVariable Long id) {
        return clientService.deactivate(id);

    }

    @GetMapping("/reactivate/{id}")
    public Map<String, Object> reactivate(@PathVariable Long id) {
        return clientService.reactivate(id);

    }

    @GetMapping("/notAuthorized")
    public String notAuthorized() {
        return "You are not authorized to do it !";


    }

}
