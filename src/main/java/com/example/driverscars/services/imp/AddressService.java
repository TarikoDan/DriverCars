package com.example.driverscars.services.imp;

import com.example.driverscars.dao.AddressDAO;
import com.example.driverscars.dao.DriverDao;
import com.example.driverscars.dto.response.AddressResponse;
import com.example.driverscars.entiti.Address;
import com.example.driverscars.entiti.Driver;
import com.example.driverscars.services.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressDAO addressDAO;
    @Autowired
    DriverDao driverDao;

    @Override
    public AddressResponse insert(Address address) {
        if (addressDAO.findAll().contains(address)) {
            log.error("Such an object already exists");
//            throw  new IOException("Such an object already exists");
            return null;
        }
        addressDAO.save(address);
        return convertToResponse(address);
    }

    @Override
    public AddressResponse insert(Address address, int driverId) {
        if (addressDAO.findAll().contains(address)) {
            log.error("Such an object already exists");
            return null;
        }
        final Driver driver = driverDao.findById(driverId).orElseThrow(() ->
                new NullPointerException("there is no Driver with Id: " + driverId));
        address.getDrivers().add(driver);
        addressDAO.save(address);
        return convertToResponse(address);
    }

    @Override
    public List<AddressResponse> getAll() {
        List<AddressResponse> addressResponses = new ArrayList<>();
        addressDAO.findAll().forEach(address -> addressResponses.add(convertToResponse(address)));
        return addressResponses;
    }

    @Override
    public AddressResponse getById(int id) {
        return convertToResponse(
                addressDAO.findById(id).orElseThrow(() ->
                new NullPointerException("No such Address with Id: " + id))
        );
    }

    @Override
    public AddressResponse setDriverToAddress(int id, int driverId) {
        final Address address = addressDAO.findById(id).orElseThrow(() ->
                new NullPointerException("No such Address with Id: " + id));
        final Driver driver = driverDao.findById(driverId).orElseThrow(() ->
                new NullPointerException("No such Driver with Id: " + driverId));
        if (address.getDrivers().contains(driver)) {
            log.error("Such an Driver already exists in this Address");
            return null;
        }
        address.getDrivers().add(driver);
        addressDAO.save(address);

        return convertToResponse(address);
    }

    @Override
    public void deleteById(int id) {
        addressDAO.deleteById(id);
    }

    AddressResponse convertToResponse(Address address) {
        Map<Integer, String> drivers = new HashMap<>();
        if (!address.getDrivers().isEmpty()) {
            address.getDrivers().forEach(driver ->
                    drivers.put(driver.getId(), driver.getName())
            );
        }
        return AddressResponse.builder()
                .id(address.getId())
                .postCode(address.getPostCode())
                .city(address.getCity())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .drivers(drivers)
                .build();
    }
}
