package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.entity.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    List<Device> findAll();
    Device findByName(String name);
}
