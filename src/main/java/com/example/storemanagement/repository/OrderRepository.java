package com.example.storemanagement.repository;

import com.example.storemanagement.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT c FROM Order c WHERE c.status = :status ")
    List<Order> findAllCommands(String status);

}
