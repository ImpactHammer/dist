package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.entity.SupportRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(path = "support-request", exported = false)
public interface SupportRequestRepository extends CrudRepository<SupportRequest, Long> {
    List<SupportRequest> findAll();
    SupportRequest findByName(String name);
    List<SupportRequest> findByStatus(String status);
    List<SupportRequest> findByClient_Name(String name);
    List<SupportRequest> findByMaster_Name(String name);
}
