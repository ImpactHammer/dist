package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.entity.DiagnosticsResponse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnosticsResponseRepository extends CrudRepository<DiagnosticsResponse, Long> {
    List<DiagnosticsResponse> findAll();
}
