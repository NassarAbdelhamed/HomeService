package com.application.homeServices.dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customerdata implements MyDate {
    private long userId;
    private String email;
    private String name;
    private  String phoneNumber;
    private int age;
}
