package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.entity.Client;
import com.example.servingwebcontent.entity.DeviceInstance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceInstanceRepository extends CrudRepository<DeviceInstance, Long> {
    List<DeviceInstance> findAll();
    List<DeviceInstance> findByClient_Name(String name);
    List<DeviceInstance> findByClient(Client client);
}
