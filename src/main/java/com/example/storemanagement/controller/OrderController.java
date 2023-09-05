package com.example.storemanagement.controller;

import com.example.storemanagement.entities.Order;
import com.example.storemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/commands")
public class OrderController {

    @Autowired
    private OrderService commandService;

    @GetMapping("/getCommand")
    public Order getCommand(@RequestParam Long id) {
        return commandService.getCommandById(id);
    }

    @GetMapping("/getAll")
    public List<Order> getAllCommands() {
        return commandService.getAllCommands();
    }

    @GetMapping("/delete")
    public void deleteCommandById(Long id) {
        commandService.delete(id);
    }
    @GetMapping("/deleteAll")
    public void deleteAllCommand() {
        commandService.deleteAll();
    }


}
