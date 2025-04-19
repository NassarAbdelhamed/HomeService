package com.application.homeServices.dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Req {
    private long userId;
    private long workerId;
    private String address;
    private String description;
    private String latitude;
    private String longitude;
}
