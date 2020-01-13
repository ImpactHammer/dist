package com.example.servingwebcontent.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "device_instance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = Device.class)
    Device device;

    @ManyToOne(targetEntity = Client.class)
    Client client;

    String status;

    public DeviceInstance(Device device, Client client, String status) {
        this.device = device;
        this.client = client;
        this.status = status;
    }
}
