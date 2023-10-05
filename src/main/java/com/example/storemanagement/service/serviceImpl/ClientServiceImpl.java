package com.example.storemanagement.service.serviceImpl;

import com.example.storemanagement.entities.Client;
import com.example.storemanagement.entities.Role;
import com.example.storemanagement.model.ClientDto;
import com.example.storemanagement.model.LoginDto;
import com.example.storemanagement.repository.ClientRepository;
import com.example.storemanagement.repository.RoleRepository;
import com.example.storemanagement.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.example.storemanagement.constants.Constants.*;

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
        return clientRepository.findAll();
    }

    @Override
    public Map<String, Object> deactivate(Long id) {
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
        map.put(MESSAGE, "The user has been deactivated successfully");
        return map;

    }

    @Override
    public Map<String, Object> reactivate(Long id) {
        Map<String, Object> map = new HashMap<>();
        Client byId = clientRepository.findById(id)
                .orElse(null);

        if (byId == null) {
            map.put(SUCCESS, false);
            map.put(MESSAGE, "User not found");

            return map;
        }

        byId.setStatus("ACTIVE");
        clientRepository.save(byId);
        map.put(SUCCESS, true);
        map.put(MESSAGE, "The user has been reactivated successfully");
        return map;

    }

    @Override
    public Map<String, Object> signup(ClientDto clientDto) {
        Map<String, Object> map = new HashMap<>();
        Set<Role> roles = new HashSet<>();
        Client client = clientRepository.findByUsername(clientDto.getUsername()).orElse(null);

        if (!Objects.isNull(client)) {
            map.put(SUCCESS, false);
            map.put(MESSAGE, "This username ' " + clientDto.getUsername() + " ' has been already taken");

            return map;
        }

        if (!Objects.isNull(clientDto.getRoleDto())) {
            clientDto.getRoleDto().forEach(roleDto -> {
                Role role = roleRepository.findByRoleName(roleDto.getRoleName());
                roles.add(role);
            });
        } else {
            Role role = roleRepository.findByRoleName("USER");
            roles.add(role);
        }

        client = Client.builder()
                .username(clientDto.getUsername())
                .password(passwordEncoder.encode(clientDto.getPassword()))
                .email(clientDto.getEmail())
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .phone(clientDto.getPhone())
                .status("ACTIVE")
                .roles(roles)
                .build();

        clientRepository.save(client);

        map.put(SUCCESS, true);
        map.put(MESSAGE, "Your registration has been succeeded");

        return map;

    }

    @Override
    public Map<String, Object> update(ClientDto clientDto) {
        Map<String, Object> map = new HashMap<>();
        Client client = clientRepository.findById(clientDto.getId()).orElse(null);

        if (Objects.isNull(client)) {
            map.put(SUCCESS, false);
            map.put(MESSAGE, "user not found");
            return map;
        }

        if (!Objects.isNull(clientDto.getFirstName()))
            client.setFirstName(clientDto.getFirstName());
        if (!Objects.isNull(clientDto.getLastName()))
            client.setLastName(clientDto.getLastName());
        if (!Objects.isNull(clientDto.getPhone()))
            client.setPhone(clientDto.getPhone());
        if (!Objects.isNull(clientDto.getEmail()))
            client.setPhone(clientDto.getEmail());

        clientRepository.save(client);
        map.put(SUCCESS, true);
        map.put(MESSAGE, "user has been updated successfully ");
        return map;
    }

    public Map<String, Object> authenticate(LoginDto loginDto) {
        Map<String, Object> map = new HashMap<>();

        Client client = clientRepository.findByUsername(loginDto.getUsername()).orElse(null);

        if (Objects.isNull(client)) {
            map.put(SUCCESS, Boolean.FALSE);
            map.put(MESSAGE, USER_NOT_FOUND);
            return map;
        }

        if (!passwordEncoder.matches(loginDto.getPassword(), client.getPassword())){
            map.put(SUCCESS, Boolean.FALSE);
            map.put(MESSAGE, WRONG_PASS);
            return map;
        }

        map.put(CONNECTED_USER, client);
        map.put(SUCCESS, Boolean.TRUE);

        return map;
    }
}
