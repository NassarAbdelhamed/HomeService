package com.application.homeServices.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_profile")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id",unique = true)
    private long userId;
    private String name;

    public CustomerProfile(long id, String name) {
        this.userId=id;
        this.name=name;
    }
}
