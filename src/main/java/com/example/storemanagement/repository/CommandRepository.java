package com.example.storemanagement.repository;

import com.example.storemanagement.entities.Client;
import com.example.storemanagement.entities.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
    @Query("SELECT c FROM Command c WHERE c.status = :status ")
    List<Command> findAllCommands(String status);

}
