package com.example.servingwebcontent.controller.master.form;

import com.example.servingwebcontent.entity.SupportRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloseSupportRequest {
    String masterName;
    SupportRequest supportRequest;
}
