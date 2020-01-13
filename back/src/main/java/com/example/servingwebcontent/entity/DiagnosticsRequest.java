package com.example.servingwebcontent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diagnostics_request")
@Data
@AllArgsConstructor
public class DiagnosticsRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = Master.class)
    Master master;

    String name;

    @OneToOne(targetEntity = DeviceInstance.class)
    DeviceInstance deviceInstance;

    String status;

    public DiagnosticsRequest() {
    }

    public DiagnosticsRequest(String name, Master master, DeviceInstance deviceInstance, String status) {
        this.name = name;
        this.master = master;
        this.deviceInstance = deviceInstance;
        this.status = status;
    }
}
