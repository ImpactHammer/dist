package com.example.servingwebcontent.controller.master.form;

import com.example.servingwebcontent.entity.DiagnosticsRequest;
import com.example.servingwebcontent.entity.DiagnosticsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloseDiagnosticsRequest {
    String masterName;
    DiagnosticsResponse diagnosticsResponse;
}
