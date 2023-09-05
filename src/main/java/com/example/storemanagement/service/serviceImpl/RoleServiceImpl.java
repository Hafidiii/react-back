package com.example.storemanagement.service.serviceImpl;

import com.example.storemanagement.entities.Role;
import com.example.storemanagement.repository.RoleRepository;
import com.example.storemanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRoles(List<String> rolesName) {

        List<Role> roles = new ArrayList<>();

        rolesName.forEach(roleName -> {
            Role role = Role.builder()
                    .roleName(roleName)
                    .build();

            roles.add(role);

        });

        roleRepository.saveAll(roles);
    }
}
