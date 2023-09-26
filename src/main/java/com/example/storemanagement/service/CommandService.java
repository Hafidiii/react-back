package com.example.storemanagement.service;

import com.example.storemanagement.entities.Command;
import com.example.storemanagement.model.CommandDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CommandService {

    Map<String, Object> save(CommandDto commandDto);
    Map<String, Object> submit(Long id);

    Map<String, Object> reject(Long id);

    Command getCommandById(Long id);

    Map<String, Object> getAll();

    void delete(Long id);
}
