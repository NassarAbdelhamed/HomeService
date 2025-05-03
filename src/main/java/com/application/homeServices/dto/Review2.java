package com.application.homeServices.dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review2 {
    private long userId;
    private String name;
    private double rate;
    private  String comment;
}
