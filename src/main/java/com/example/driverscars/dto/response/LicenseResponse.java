package com.example.driverscars.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LicenseResponse {
    private int id;
    private String seriesNumber;
    private LocalDate date;
    private String driverName;

}
