package com.example.storemanagement.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {

    void addRoles(List<String> rolesName);
}
