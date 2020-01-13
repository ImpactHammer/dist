package com.example.servingwebcontent.controller.manager;

import com.example.servingwebcontent.controller.manager.form.AssignDiagnosticsRequest;
import com.example.servingwebcontent.controller.manager.form.AssignSupportRequest;
import com.example.servingwebcontent.controller.manager.form.GetLists;
import com.example.servingwebcontent.controller.master.form.AssignDeviceToClient;
import com.example.servingwebcontent.controller.master.form.GetSupportRequestList;
import com.example.servingwebcontent.entity.*;
import com.example.servingwebcontent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("http://localhost:4200")
class ManagerRESTController
{
    @Autowired
    private SupportRequestRepository supportRequestRepository;
    @Autowired
    private DiagnosticsRequestRepository diagnosticsRequestRepository;
    @Autowired
    private DiagnosticsResponseRepository diagnosticsResponseRepository;
    @Autowired
    private DeviceRequestRepository deviceRequestRepository;
    @Autowired
    private DeviceInstanceRepository deviceInstanceRepository;
    @Autowired
    private MasterRepository masterRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = "/manager/lists")
    public GetLists getLists(@RequestParam String clientName) {
        List<SupportRequest> pendingSupportRequestList = supportRequestRepository.findByStatus("pending");
        List<DeviceRequest> deviceRequestList = deviceRequestRepository.findAll();
        List<DeviceInstance> totalDeviceInstanceList = deviceInstanceRepository.findByClient(null);
        List<Master> freeMasterList = masterRepository.findByStatus("free");
        List<Client> clientList = clientRepository.findAll();
        List<DiagnosticsResponse> diagnosticsResponseList = diagnosticsResponseRepository.findAll();
        Map<Long, List<DeviceInstance>> clientDevices = new HashMap<>();
        for (Client client : clientRepository.findAll()) {
            clientDevices.put(client.getId(), deviceInstanceRepository.findByClient(client));
        }
        return new GetLists(pendingSupportRequestList, deviceRequestList, totalDeviceInstanceList,
                freeMasterList, clientList, clientDevices, diagnosticsResponseList);
    }

    @PostMapping(value = "/manager/assign-support-request")
    public void assignSupportRequest(@RequestBody AssignSupportRequest data) {
        Master master = data.getMaster();
        SupportRequest supportRequest = data.getSupportRequest();
        supportRequest.setMaster(master);
        supportRequest.setStatus("assigned");
        supportRequestRepository.save(supportRequest);
    }

    @PostMapping(value = "/manager/assign-diagnostics-request")
    public void assignDiagnosticsRequest(@RequestBody AssignDiagnosticsRequest data) {
        Master master = data.getMaster();
        DeviceInstance deviceInstance = data.getDeviceInstance();
        String name = data.getDiagnosticsRequestName();
        String status = "assigned";
        DiagnosticsRequest diagnosticsRequest = new DiagnosticsRequest(name, master, deviceInstance, status);
        diagnosticsRequestRepository.save(diagnosticsRequest);
    }

    @PostMapping(value = "/manager/assign-device-to-client")
    public void assignDeviceToClient(@RequestBody AssignDeviceToClient data) {
        DeviceInstance deviceInstance = data.getDeviceInstance();
        deviceInstance.setClient(data.getClient());
        System.out.println(deviceInstance);
        deviceInstanceRepository.save(deviceInstance);
    }

    @PostMapping(value = "/manager/unassign-device-from-client")
    public void unassignDeviceFromlient(@RequestBody DeviceInstance data) {
        data.setClient(null);
        System.out.println(data);
        System.out.println(deviceInstanceRepository.findById(data.getId()));
        deviceInstanceRepository.save(data);
    }
}
