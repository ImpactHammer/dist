package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.entity.DeviceRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRequestRepository extends CrudRepository<DeviceRequest, Long> {
    List<DeviceRequest> findAll();
}
