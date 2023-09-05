package com.example.storemanagement.service.serviceImpl;

import com.example.storemanagement.entities.Client;
import com.example.storemanagement.entities.Roles;
import com.example.storemanagement.repository.ClientRepository;
import com.example.storemanagement.repository.RoleRepository;
import com.example.storemanagement.service.ClientService;
import com.example.storemanagement.model.ClientDto;
import com.example.storemanagement.model.LoginDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.example.storemanagement.constants.Constants.MESSAGE;
import static com.example.storemanagement.constants.Constants.SUCCESS;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Client getUserByUserName(String username) {
        Optional<Client> byUsername = clientRepository.findByUsername(username);
        return byUsername.orElse(null);

    }

    @Override
    public List<Client> getAllUsers() {
        return clientRepository.findAllClients("ACTIVE");
    }

    @Override
    public Map<String, Object> remove(Long id) {
        Map<String, Object> map = new HashMap<>();
        Client byId = clientRepository.findById(id)
                .orElse(null);

        if (byId == null) {
            map.put(SUCCESS, false);
            map.put(MESSAGE, "User not found");

            return map;
        }

        byId.setStatus("INACTIVE");
        clientRepository.save(byId);
        map.put(SUCCESS, true);
        map.put(MESSAGE, "The user has been removed successfully");
        return map;

    }

    @Override
    public Client signup(ClientDto clientDto) {
        Optional<Client> client = clientRepository.findByUsername(clientDto.getUsername());

        if (client.isPresent()) throw new RuntimeException("User already exist");

        Roles roles1 = roleRepository.save(new Roles(null, "ADMIN"));
        Roles roleUser = roleRepository.findByRoleName("ADMIN");
        Set<Roles> roles = new HashSet<>();
        roles.add(roleUser);

        Client newClient = Client.builder()
                .username(clientDto.getUsername())
                .password(passwordEncoder.encode(clientDto.getPassword()))
                .eamil(clientDto.getEamil())
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .phone(clientDto.getPhone())
                .status("ACTIVE")
                .roles(roles)
                .build();

        return clientRepository.save(newClient);
    }

    @Override
    public Map<String, Object> update(ClientDto clientDto) {
        Map<String, Object> map = new HashMap<>();
       Client client = clientRepository.findById(clientDto.getId()).orElse(null);
       if (client == null){
           map.put(SUCCESS, false);
           map.put(MESSAGE, "user not found");
           return map;
       }

        Client updatedClient = Client.builder()
                .username(clientDto.getUsername())
                .password(passwordEncoder.encode(clientDto.getPassword()))
                .eamil(clientDto.getEamil())
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .build();

         clientRepository.save(updatedClient);
        map.put(SUCCESS, true);
        map.put(MESSAGE, "user has been updated successfully ");
        return map;
    }

    public Map<String, Object> authenticate(LoginDto loginDto) {
        Map<String, Object> map = new HashMap<>();

        Optional<Client> client = clientRepository.findByUsername(loginDto.getUsername());
        if (client.isEmpty()) throw new RuntimeException("Username not exist");
        if (!Objects.equals(loginDto.getPassword(), client.get().getPassword()))
            throw new RuntimeException("wrong password");

        map.put("connectedUser", client.get());
        map.put("success", true);

        return map;
    }
}
