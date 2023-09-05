package com.example.storemanagement.repository;

import com.example.storemanagement.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.status = :status ")
    List<Client> findAllClients(String status);

    Optional<Client> findByUsername(String username);
}
