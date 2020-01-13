package com.example.servingwebcontent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "support_request")
@Data
@AllArgsConstructor
public class SupportRequest extends WorkRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = Client.class)
    Client client;

    @ManyToOne(targetEntity = Master.class)
    Master master;

    String name;

    String description;

    String status;

    public SupportRequest() {
    }

    public SupportRequest(Client client, Master master, String name, String description, String status) {
        this.client = client;
        this.master = master;
        this.name = name;
        this.description = description;
        this.status = status;
    }


}
