package com.example.servingwebcontent.controller.manager.form;

import com.example.servingwebcontent.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class GetLists {
    List<SupportRequest> pendingSupportRequestList;
    List<DeviceRequest> deviceRequestList;
    List<DeviceInstance> totalDeviceInstanceList;
    List<Master> freeMasterList;
    List<Client> clientList;
    Map<Long, List<DeviceInstance>> clientDevices;
    List<DiagnosticsResponse> diagnosticsResponseList;
}
