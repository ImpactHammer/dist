package com.example.servingwebcontent.controller.manager.form;

import com.example.servingwebcontent.entity.DeviceInstance;
import com.example.servingwebcontent.entity.DiagnosticsRequest;
import com.example.servingwebcontent.entity.Master;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignDiagnosticsRequest {
    String diagnosticsRequestName;
    DeviceInstance deviceInstance;
    Master master;
}
