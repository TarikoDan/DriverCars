package com.example.driverscars.services.imp;

import com.example.driverscars.dao.CarDao;
import com.example.driverscars.dao.DriverDao;
import com.example.driverscars.dto.response.CarResponse;
import com.example.driverscars.entiti.Car;
import com.example.driverscars.entiti.Driver;
import com.example.driverscars.services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService implements ICarService {

    @Autowired
    CarDao carDao;
    @Autowired
    DriverDao driverDao;

    @Override
    public CarResponse insert(Car car, int driverId) {
        Driver driver = driverDao.findById(driverId).orElseThrow(() ->
                new NullPointerException("No such Driver with id = " + driverId));
        car.setDriver(driver);
        car = carDao.save(car);
        return convertToResponse(car);
    }

    @Override
    public List<CarResponse> getAll() {
        ArrayList<CarResponse> cars = new ArrayList<>();
        carDao.findAll().forEach(car -> cars.add(convertToResponse(car)));
        return cars;
    }

    @Override
    public CarResponse getById(int id) {
        Car car = carDao.findById(id)
                .orElseThrow(() -> new NullPointerException("there is no Car with Id: " + id));
        return convertToResponse(car);
    }

    @Override
    public CarResponse edit(int id, Car car) {
        if (carDao.existsById(id)) {
            car.setId(id);
            car.setDriver(carDao.getOne(id).getDriver());
            car = carDao.saveAndFlush(car);
            return convertToResponse(car);
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        boolean exists = carDao.existsById(id);
        carDao.deleteById(id);
        return exists;
    }

    @Override
    public void deleteAll() {
        carDao.deleteAll();
    }

    private CarResponse convertToResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .model(car.getModel())
                .year(car.getYear())
                .driverName(car.getDriver().getName())
                .build();
    }
}
