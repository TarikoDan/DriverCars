package com.example.driverscars.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarResponse {
    private int id;
    private String model;
    private int year;
    private String driverName;
}
