package com.application.homeServices.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Worker_profile ")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
    private String name;
    private String jobTittle;
    private String address;
    private String latitude;
    private String longitude;
    private int age;
    private String phoneNumber;
    private String skills;
    private String credentials;

    public WorkerProfile (long id, String name,String jobTittle,String address,String latitude,String longitude,int age,String phoneNumber, String skills) {
        this.userId=id;
        this.name=name;
        this.jobTittle=jobTittle;
        this.latitude=latitude;
        this.longitude=longitude;
        this.address=address;
        this.age=age;
        this.phoneNumber=phoneNumber;
        this.skills=skills;
    }
}
