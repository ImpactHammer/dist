package com.example.servingwebcontent.controller.master.form;

import com.example.servingwebcontent.entity.DiagnosticsRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeclineDiagnosticsRequest {
    String masterName;
    DiagnosticsRequest diagnosticsRequest;
}
