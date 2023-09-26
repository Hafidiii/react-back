package com.example.storemanagement.service.serviceImpl;

import com.example.storemanagement.entities.Client;
import com.example.storemanagement.entities.Command;
import com.example.storemanagement.entities.Product;
import com.example.storemanagement.model.CommandDto;
import com.example.storemanagement.repository.ClientRepository;
import com.example.storemanagement.repository.CommandRepository;
import com.example.storemanagement.repository.ProductRepository;
import com.example.storemanagement.service.CommandService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.storemanagement.constants.Constants.MESSAGE;
import static com.example.storemanagement.constants.Constants.SUCCESS;

@Service
public class CommandServiceImpl implements CommandService {

    private final CommandRepository commandRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    @Autowired
    CommandServiceImpl(CommandRepository commandRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.commandRepository = commandRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Map<String, Object> save(CommandDto commandDto) {
        Map<String, Object> results = new HashMap<>();
        List<Product> products = Lists.newArrayList();
        try {

            commandDto.getProductDto().forEach(product -> {
                Product byId = productRepository.findById(product.getId()).orElse(null);
                products.add(byId);
            });

            Client client = clientRepository.findById(commandDto.getClientDto().getId()).orElse(null);

            if (Objects.isNull(client) || products.isEmpty()) {
                results.put(SUCCESS, false);
                results.put(MESSAGE, "operation failed");
                return results;
            }

            Command command = new Command();

            command.setClient(client);
            command.setStatus("CREATED");
            command.setCreatedDate(LocalDateTime.now());
            command.setUpdatedDate(LocalDateTime.now());
            command.getProducts().addAll(products
                    .stream()
                    .peek(p -> p.getCommand().add(command))
                    .toList());

            commandRepository.save(command);

            results.put(SUCCESS, true);
            results.put(MESSAGE, "command has been created");

        } catch (Exception e) {
            e.printStackTrace();
            results.put(SUCCESS, false);
            results.put(MESSAGE, "Technical error has occurred");
        }

        return results;

    }

    @Override
    public Map<String, Object> submit(Long id) {
        Map<String, Object> map = new HashMap<>();

        Command command = commandRepository.findById(id).orElse(null);

        if (!Objects.isNull(command)) {
            command.setStatus("SUBMITTED");
            command.setUpdatedDate(LocalDateTime.now());

            commandRepository.save(command);

            map.put(SUCCESS, true);
            map.put(MESSAGE, "command has been submitted");
            return map;
        }

        map.put(SUCCESS, false);
        map.put(MESSAGE, "command id not found");
        return map;

    }

    @Override
    public Map<String, Object> reject(Long id) {
        Map<String, Object> results = new HashMap<>();
        Command command = commandRepository.findById(id).orElse(null);

        if (!Objects.isNull(command)) {
            command.setStatus("REJECTED");
            command.setUpdatedDate(LocalDateTime.now());

            commandRepository.save(command);

            results.put(SUCCESS, true);
            results.put(MESSAGE, "command has been rejected");
            return results;
        }

        results.put(SUCCESS, false);
        results.put(MESSAGE, "command id not found");
        return results;
    }

    @Override
    public Command getCommandById(Long id) {
        return commandRepository.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> getAll() {
        Map<String, Object> map = Maps.newHashMap();
        List<Command> all = commandRepository.findAll();
        map.put("commands", all);
        map.put("totalElements", all.size());
        return map;
    }

    @Override
    public void delete(Long id) {
        commandRepository.deleteById(id);
    }

}
