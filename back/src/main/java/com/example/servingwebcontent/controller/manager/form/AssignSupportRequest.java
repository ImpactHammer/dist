package com.example.servingwebcontent.controller.manager.form;

import com.example.servingwebcontent.entity.Master;
import com.example.servingwebcontent.entity.SupportRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignSupportRequest {
    SupportRequest supportRequest;
    Master master;
}
