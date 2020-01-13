package com.example.servingwebcontent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "device_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany()
    List<DeviceBunch> deviceBunchList;

//    @OneToMany(targetEntity = DeviceDescription.class)
//    List<DeviceDescription> deviceDescriptionList;

    @ManyToOne(targetEntity = SupportRequest.class)
    SupportRequest supportRequest;

    public DeviceRequest(SupportRequest supportRequest, List<DeviceBunch> deviceBunchList) {
        this.supportRequest = supportRequest;
        this.deviceBunchList = deviceBunchList;
    }
}
