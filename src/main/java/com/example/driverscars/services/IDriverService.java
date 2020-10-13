package com.example.driverscars.services;

import com.example.driverscars.entiti.Driver;

import java.util.List;

public interface IDriverService {
    Driver insert(Driver driver);

    List<Driver> getAll();

    Driver getById(int id);

    Driver edit (int id, Driver driver);

    void deleteById (int id);

    void deleteAll ();

}
