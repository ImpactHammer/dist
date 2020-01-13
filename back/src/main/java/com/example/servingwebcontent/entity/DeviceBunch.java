package com.example.servingwebcontent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "device_bunch")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceBunch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne()
    Device device;

//    @ManyToOne(targetEntity = DeviceRequest.class)
//    DeviceRequest deviceRequest;

    int count;

    public DeviceBunch(Device device, int count) {
        this.device = device;
        this.count = count;
    }
}
