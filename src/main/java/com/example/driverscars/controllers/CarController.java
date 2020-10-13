package com.example.driverscars.controllers;

import com.example.driverscars.dto.response.CarResponse;
import com.example.driverscars.entiti.Car;
import com.example.driverscars.services.ICarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cars")
public class CarController {

    private final ICarService carService;

    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @PostMapping("/driver/{driverId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponse insert(@RequestBody @Valid Car car, @PathVariable int driverId) {

        return carService.insert(car,driverId);
    }

    @GetMapping
    public List<CarResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping(value = "/{id}")
    public CarResponse getById(@PathVariable int id) {
        return carService.getById(id);
    }

    @PutMapping("/{id}")
    public CarResponse edit (@PathVariable int id, @RequestBody @Valid Car car) {
        return carService.edit(id, car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID (@PathVariable int id) {
         carService.deleteById(id);
    }

    @DeleteMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearAll() {
        carService.deleteAll();
    }

}
