package com.example.storemanagement.service.serviceImpl;

import com.example.storemanagement.entities.Order;
import com.example.storemanagement.repository.OrderRepository;
import com.example.storemanagement.service.OrderService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository commandRepository;

    public OrderServiceImpl(OrderRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public void validateCommend() {

    }

    @Override
    public void rejectCommend() {

    }

    @Override
    public Order getCommandById(Long id) {
        return commandRepository.findById(id).get();
    }

    @Override
    public List<Order> getAllCommands() {
        return commandRepository.findAllCommands("CONFIRMED");
    }

    @Override
    public void delete(Long id) {
        commandRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        commandRepository.deleteAll();
    }
}
