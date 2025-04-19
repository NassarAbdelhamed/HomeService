package com.application.homeServices.models;


import com.application.homeServices.dto.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long userId;
    private long workerId;
    private Status status;
    private String address;
    private String description;
    private String latitude;
    private String longitude;
    private double rate;
    private String comment;
}
