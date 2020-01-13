package com.example.servingwebcontent.controller.master.form;

import com.example.servingwebcontent.entity.DiagnosticsRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcceptDiagnosticsRequest {
    String masterName;
    DiagnosticsRequest diagnosticsRequest;
}
