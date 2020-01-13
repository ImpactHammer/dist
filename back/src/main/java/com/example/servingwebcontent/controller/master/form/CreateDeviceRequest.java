package com.example.servingwebcontent.controller.master.form;

import com.example.servingwebcontent.entity.DeviceBunch;
import com.example.servingwebcontent.entity.SupportRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeviceRequest {
    List<DeviceBunch> deviceBunchList;
    SupportRequest supportRequest;

    public CreateDeviceRequest(SupportRequest supportRequest, List<DeviceBunch> deviceBunchList) {
        this.supportRequest = supportRequest;
        this.deviceBunchList = deviceBunchList;
    }
}
