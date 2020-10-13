package com.example.driverscars.services;

import com.example.driverscars.dto.response.CarResponse;
import com.example.driverscars.entiti.Car;

import java.util.List;

public interface ICarService {
    CarResponse insert(Car car, int driverId);

    List<CarResponse> getAll();

    CarResponse getById(int id);

    CarResponse edit (int id, Car car);

    boolean deleteById (int id);

    void deleteAll ();

}
