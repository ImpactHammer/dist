package com.example.servingwebcontent.controller.master.form;

import com.example.servingwebcontent.entity.Device;
import com.example.servingwebcontent.entity.DiagnosticsRequest;
import com.example.servingwebcontent.entity.SupportRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetSupportRequestList {
    List<SupportRequest> assignedSupportRequestList;
    List<SupportRequest> acceptedSupportRequestList;
    List<DiagnosticsRequest> assignedDiagnosticsRequestList;
    List<DiagnosticsRequest> acceptedDiagnosticsRequestList;
    List<Device> deviceFromStorageList;
}
