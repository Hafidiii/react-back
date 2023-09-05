package com.example.storemanagement.service;

import com.example.storemanagement.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public void validateCommend();
    public void rejectCommend();

    public Order getCommandById(Long id);

    public List<Order> getAllCommands();

    public void delete(Long id);
    public void deleteAll();
}
