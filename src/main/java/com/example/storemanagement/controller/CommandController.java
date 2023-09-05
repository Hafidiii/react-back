package com.example.storemanagement.controller;

import com.example.storemanagement.entities.Command;
import com.example.storemanagement.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/commands")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @GetMapping("/getCommand")
    public Command getCommand(@RequestParam Long id) {
        return commandService.getCommandById(id);
    }

    @GetMapping("/getAll")
    public List<Command> getAllCommands() {
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
