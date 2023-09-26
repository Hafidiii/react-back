package com.example.storemanagement.controller;

import com.example.storemanagement.entities.Command;
import com.example.storemanagement.model.CommandDto;
import com.example.storemanagement.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/commands")
public class CommandController {

    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping("/all")
    public Map<String, Object> getAllCommands() {
        return commandService.getAll();
    }

    @GetMapping("/{id}")
    public Command getCommandById(@PathVariable Long id) {
        return commandService.getCommandById(id);
    }

    @DeleteMapping("/{id}")
    void deleteCommandById(@PathVariable Long id) {
        commandService.delete(id);
    }

    @PostMapping("/save")
    Map<String, Object> save(@RequestBody CommandDto commandDto) {
        return commandService.save(commandDto);
    }

    @GetMapping("/submit/{id}")
    Map<String, Object> submit(@PathVariable Long id) {
        return commandService.submit(id);
    }

    @GetMapping("/reject/{id}")
    Map<String, Object> reject(@PathVariable Long id) {
        return commandService.reject(id);
    }

}
