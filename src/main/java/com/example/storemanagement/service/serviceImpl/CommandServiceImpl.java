package com.example.storemanagement.service.serviceImpl;

import com.example.storemanagement.entities.Command;
import com.example.storemanagement.repository.CommandRepository;
import com.example.storemanagement.service.CommandService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandServiceImpl implements CommandService {

    private CommandRepository commandRepository;

    public CommandServiceImpl(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public void validateCommend() {

    }

    @Override
    public void rejectCommend() {

    }

    @Override
    public Command getCommandById(Long id) {
        return commandRepository.findById(id).get();
    }

    @Override
    public List<Command> getAllCommands() {
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
