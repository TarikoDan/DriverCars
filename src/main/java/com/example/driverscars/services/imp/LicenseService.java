package com.example.driverscars.services.imp;

import com.example.driverscars.dao.DriverDao;
import com.example.driverscars.dao.LicenseDao;
import com.example.driverscars.dto.response.LicenseResponse;
import com.example.driverscars.entiti.Driver;
import com.example.driverscars.entiti.License;
import com.example.driverscars.services.ILicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LicenseService implements ILicenseService {

    @Autowired
    LicenseDao licenseDao;
    @Autowired
    DriverDao driverDao;

    @Override
    public LicenseResponse insert(License license, int driverId) {
        Driver driver = driverDao.findById(driverId).orElseThrow(() ->
                new NullPointerException("No such Driver with id = " + driverId));
        license.setDriver(driver);
        license = licenseDao.save(license);
        return convertToResponse(license);
    }

    @Override
    public List<LicenseResponse> getAll() {
        ArrayList<LicenseResponse> licenseResponse = new ArrayList<>();
        licenseDao.findAll().forEach(license -> licenseResponse.add(convertToResponse(license)));
        return licenseResponse;
    }

    @Override
    public LicenseResponse getById(int id) {
        License license = licenseDao.findById(id)
                .orElseThrow(() -> new NullPointerException("there is no License with Id: " + id));
        return convertToResponse(license);
    }

    @Override
    public LicenseResponse edit(int id, License license) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (licenseDao.existsById(id)) {
            licenseDao.deleteById(id);
        }else {
            throw new NullPointerException("there is no License with Id: " + id);
        }
    }

    @Override
    public void deleteAll() {

    }

    private LicenseResponse convertToResponse(License license) {
        return LicenseResponse.builder()
                .id(license.getId())
                .seriesNumber(
                        license.getSeries()
                        .concat(":")
                        + (license.getId())
                        + "-"
                        + license.getDistrict().toUpperCase()
                        .concat("-")
                        + license.getDate().getYear()
                )
                .date(license.getDate())
                .driverName(license.getDriver().getName())
                .build();
    }
}
