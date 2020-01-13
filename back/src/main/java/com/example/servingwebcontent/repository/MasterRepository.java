package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.entity.Master;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MasterRepository extends CrudRepository<Master, Long> {
    List<Master> findAll();
    List<Master> findByStatus(String status);
    Master findByName(String name);
}
