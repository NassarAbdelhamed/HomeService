package com.application.homeServices.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workerdata implements MyDate {
    private long WorkerId;
    private String email;
    private String name;
    private String jobTittle;
    private String address;
    private String latitude;
    private String longitude;
}
