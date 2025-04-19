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

    public WorkerProfile (long id, String name,String jobTittle,String address,String latitude,String longitude) {
        this.userId=id;
        this.name=name;
        this.jobTittle=jobTittle;
        this.latitude=latitude;
        this.longitude=longitude;
        this.address=address;
    }
}
