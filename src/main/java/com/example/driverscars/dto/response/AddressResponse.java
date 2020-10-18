package com.example.driverscars.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class AddressResponse {
    private int id;
    private int postCode;
    private String city;
    private String street;
    private int houseNumber;
    private Map<Integer, String> drivers;
}
