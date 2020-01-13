package com.example.servingwebcontent.controller.client;

import com.example.servingwebcontent.controller.client.form.ClientRequestForm;
import com.example.servingwebcontent.entity.Client;
import com.example.servingwebcontent.entity.Master;
import com.example.servingwebcontent.entity.SupportRequest;
import com.example.servingwebcontent.repository.ClientRepository;
import com.example.servingwebcontent.repository.SupportRequestRepository;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
class ClientRESTController
{
    @Autowired
    private SupportRequestRepository supportRequestRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = "/support-requests")
    public List<SupportRequest> getSupportRequestList(@RequestParam String clientName) {
        List<SupportRequest> result = supportRequestRepository.findByClient_Name(clientName);
        return result;
    }

    @PostMapping(path = "/support-requests", consumes = "application/x-www-form-urlencoded")
    SupportRequest createOrSaveSupportRequest(ClientRequestForm form) {
        Client client = clientRepository.findByName(form.getClientName());
        if (client == null) {
            client = new Client(form.getClientName());
            clientRepository.save(client);
        }
        String supportRequestStatus = "pending";
        Master master = null;
        SupportRequest supportRequest = new SupportRequest(client, master, form.getName(),
                form.getDescription(), supportRequestStatus);
        return supportRequestRepository.save(supportRequest);
    }

    @PostMapping(path = "/client/close-support-request")
    void closeSupportRequest(@RequestBody SupportRequest data) {
        System.out.println(data);
        supportRequestRepository.deleteById(data.getId());
    }

    @GetMapping("/support-requests/{id}")
    SupportRequest getSupportRequestById(@PathVariable Long id) {
        return supportRequestRepository.findById(id).get();
    }

    @DeleteMapping("/employees/{id}")
    void deleteSupportRequest(@PathVariable Long id) {
        supportRequestRepository.deleteById(id);
    }
}
