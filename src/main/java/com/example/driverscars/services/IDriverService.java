package com.example.driverscars.services;

import com.example.driverscars.entiti.Address;
import com.example.driverscars.entiti.Driver;

import java.util.List;

public interface IDriverService {
    Driver insert(Driver driver);

    Driver insert(Driver driver, int addressId);

    List<Driver> getAll();

    List<Driver> getByAddress(int addressId);

    Driver getById(int id);

    Driver edit (int id, Driver driver);

    void deleteById (int id);

    void deleteAll ();

}
