package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.entity.DiagnosticsRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnosticsRequestRepository extends CrudRepository<DiagnosticsRequest, Long> {
    List<DiagnosticsRequest> findAll();
    DiagnosticsRequest findByName(String name);
    List<DiagnosticsRequest> findByMaster_Name(String name);
}
