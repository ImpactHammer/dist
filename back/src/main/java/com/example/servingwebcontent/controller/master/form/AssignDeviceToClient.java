package com.example.servingwebcontent.controller.master.form;

import com.example.servingwebcontent.entity.Client;
import com.example.servingwebcontent.entity.DeviceInstance;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignDeviceToClient {
    DeviceInstance deviceInstance;
    Client client;
}
