package com.example.driverscars.services;

import com.example.driverscars.dto.response.AddressResponse;
import com.example.driverscars.entiti.Address;

import java.util.List;

public interface IAddressService {
    AddressResponse insert(Address address);

    AddressResponse insert(Address address, int driverId);

    List<AddressResponse> getAll();

    AddressResponse getById(int id);

    AddressResponse setDriverToAddress(int id, int driverId);

    void deleteById(int id);

}
