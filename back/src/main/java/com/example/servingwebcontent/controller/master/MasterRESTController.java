package com.example.servingwebcontent.controller.master;

import com.example.servingwebcontent.controller.master.form.*;
import com.example.servingwebcontent.entity.*;
import com.example.servingwebcontent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
class MasterRESTController
{
    @Autowired
    private SupportRequestRepository supportRequestRepository;
    @Autowired
    private DiagnosticsRequestRepository diagnosticsRequestRepository;
    @Autowired
    private DiagnosticsResponseRepository diagnosticsResponseRepository;
    @Autowired
    private MasterRepository masterRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceRequestRepository deviceRequestRepository;
    @Autowired
    private DeviceBunchRepository deviceBunchRepository;


    @GetMapping(value = "/master/requests")
    public GetSupportRequestList getSupportRequestList(@RequestParam String masterName) {
        Master master = masterRepository.findByName(masterName);
        if (master == null) {
            master = new Master(masterName);
            masterRepository.save(master);
        }

        List<SupportRequest> supportRequestList = supportRequestRepository.findByMaster_Name(masterName);
        List<DiagnosticsRequest> diagnosticsRequestList = diagnosticsRequestRepository.findByMaster_Name(masterName);

        List<SupportRequest> assignedSupportRequestList = new ArrayList<>();
        List<SupportRequest> acceptedSupportRequestList = new ArrayList<>();
        List<DiagnosticsRequest> assignedDiagnosticsRequestList = new ArrayList<>();
        List<DiagnosticsRequest> acceptedDiagnosticsRequestList = new ArrayList<>();
        List<Device> deviceFromStorageList = deviceRepository.findAll();

        System.out.println(supportRequestList);

        for (Iterator<SupportRequest> it = supportRequestList.iterator(); it.hasNext();) {
            SupportRequest curRequest = it.next();
            if (curRequest.getStatus().equals("assigned")) {
                assignedSupportRequestList.add(curRequest);
            } else if (curRequest.getStatus().equals("accepted")) {
                acceptedSupportRequestList.add(curRequest);
            }
        }
        for (Iterator<DiagnosticsRequest> it = diagnosticsRequestList.iterator(); it.hasNext();) {
            DiagnosticsRequest curRequest = it.next();
            if (curRequest.getStatus().equals("assigned")) {
                assignedDiagnosticsRequestList.add(curRequest);
            } else if (curRequest.getStatus().equals("accepted")) {
                acceptedDiagnosticsRequestList.add(curRequest);
            }
        }
        return new GetSupportRequestList(assignedSupportRequestList, acceptedSupportRequestList,
                assignedDiagnosticsRequestList, acceptedDiagnosticsRequestList, deviceFromStorageList);
    }

    // Support requests

    @PostMapping(value = "/master/accept-support-request")
    public void acceptSupportRequest(@RequestBody AcceptSupportRequest data) {
        SupportRequest supportRequest = data.getSupportRequest();
        supportRequest.setMaster(masterRepository.findByName(data.getMasterName()));
        supportRequest.setStatus("accepted");
        supportRequestRepository.save(supportRequest);
    }

    @PostMapping(value = "/master/decline-support-request")
    public void declineSupportRequest(@RequestBody DeclineSupportRequest data) {
        SupportRequest supportRequest = data.getSupportRequest();
        supportRequest.setMaster(null);
        supportRequest.setStatus("pending");
        supportRequestRepository.save(supportRequest);
    }

    @PostMapping(value = "/master/close-support-request")
    public ResponseEntity<String> closeSupportRequest(@RequestBody CloseSupportRequest data) {
        SupportRequest supportRequest = data.getSupportRequest();
        SupportRequest foundSupportRequest = supportRequestRepository.findByName(supportRequest.getName());
        if (foundSupportRequest.getMaster().getName().equals(data.getMasterName())) {
            supportRequest.setStatus("closed");
            supportRequestRepository.save(supportRequest);
        } else {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Access denied");
        }
        return null;
    }

//    Diagnostics requests

    @PostMapping(value = "/master/accept-diagnostics-request")
    public void acceptDiagnosticsRequest(@RequestBody AcceptDiagnosticsRequest data) {
        DiagnosticsRequest diagnosticsRequest = data.getDiagnosticsRequest();
        System.out.println(data.getMasterName());
        System.out.println(masterRepository.findAll().get(0).getName().equals(data.getMasterName()));
        Master master = masterRepository.findByName(data.getMasterName());
        System.out.println(master);
        diagnosticsRequest.setMaster(master);
        diagnosticsRequest.setStatus("accepted");
        diagnosticsRequestRepository.save(diagnosticsRequest);
    }

    @PostMapping(value = "/master/decline-diagnostics-request")
    public void declineDiagnosticsRequest(@RequestBody DeclineDiagnosticsRequest data) {
        DiagnosticsRequest diagnosticsRequest = data.getDiagnosticsRequest();
        diagnosticsRequest.setMaster(null);
        diagnosticsRequest.setStatus("pending");
        diagnosticsRequestRepository.save(diagnosticsRequest);
    }

    @PostMapping(value = "/master/close-diagnostics-request")
    public ResponseEntity<String> closeDiagnosticsRequest(@RequestBody DiagnosticsResponse data) {
        diagnosticsResponseRepository.save(data);
        DiagnosticsRequest diagnosticsRequest = data.getDiagnosticsRequest();
        diagnosticsRequest.setStatus("closed");
        diagnosticsRequestRepository.save(diagnosticsRequest);
        return null;
    }

//    @PostMapping(value = "/master/create-device-request")
//    public void createDeviceRequest(@RequestBody DeviceRequest data) {
////        System.out.println(data.getSupportRequest());
//        deviceRequestRepository.save(data);
//        deviceBunchRepository.saveAll(data.getDeviceBunchList());
//    }


    @PostMapping(value = "/master/create-device-request")
    public void createDeviceRequest(@RequestBody CreateDeviceRequest data) {
        List<DeviceBunch> deviceBunchList = new ArrayList<>();
        for (DeviceBunch deviceBunch : data.getDeviceBunchList()) {
            deviceBunchList.add(new DeviceBunch(deviceBunch.getDevice(), deviceBunch.getCount()));
        }
        deviceBunchRepository.saveAll(deviceBunchList);
        deviceRequestRepository.save(new DeviceRequest(data.getSupportRequest(), deviceBunchList));
    }

    @PostMapping(value = "/master/create-diagnostics-response")
    public void createDiagnosticsReport(@RequestBody DiagnosticsResponse data) {
        diagnosticsResponseRepository.save(data);
    }
}
