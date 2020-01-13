package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();
    Client findByName(String name);
}
