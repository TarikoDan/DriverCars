package com.example.driverscars.services;

import com.example.driverscars.dto.response.LicenseResponse;
import com.example.driverscars.entiti.License;

import java.util.List;

public interface ILicenseService {
    LicenseResponse insert(License license, int driverId);

    List<LicenseResponse> getAll();

    LicenseResponse getById(int id);

    LicenseResponse edit (int id, License license);

    void deleteById (int id);

    void deleteAll ();

}
