package com.example.servingwebcontent.controller.master.form;

import com.example.servingwebcontent.entity.Master;
import com.example.servingwebcontent.entity.SupportRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcceptSupportRequest {
    String masterName;
    SupportRequest supportRequest;
}
