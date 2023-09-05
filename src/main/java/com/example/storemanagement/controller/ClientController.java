package com.example.storemanagement.controller;

import com.example.storemanagement.entities.Client;
import com.example.storemanagement.service.ClientService;
import com.example.storemanagement.model.ClientDto;
import com.example.storemanagement.model.LoginDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@NoArgsConstructor
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/getUserByUserName")
    public Client getUserByUserName(@RequestParam String username) {
        return clientService.getUserByUserName(username);
    }

    @GetMapping("/all")
    public List<Client> getAllUsers() {
        return clientService.getAllUsers();
    }

    @PostMapping("/signup")
    public Client signup(@RequestBody ClientDto clientDto) {
        return clientService.signup(clientDto);

    }
    @PostMapping("/update")
    public  Map<String, Object> update(@RequestBody ClientDto clientDto) {
        return clientService.update(clientDto);

    }
    @PostMapping("/login")
    public Map<String, Object> authenticate(@RequestBody LoginDto loginDto) {
        return clientService.authenticate(loginDto);

    }
    @GetMapping("/remove/{id}")
    public Map<String, Object> remove(@PathVariable Long id) {
        return clientService.remove(id);

    }
    @GetMapping("/notAuthorized")
    public String notAuthorized() {
        return "You are not authorized to do it !";


    }

}
