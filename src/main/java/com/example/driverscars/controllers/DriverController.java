package com.example.driverscars.controllers;

import com.example.driverscars.entiti.Driver;
import com.example.driverscars.services.IDriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/drivers")
@CrossOrigin(origins = "http://localhost:4200")
public class DriverController {

    private final IDriverService driverService;

    @Autowired
    public DriverController(IDriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Driver insert(@RequestBody @Valid Driver driver) {
        return driverService.insert(driver);
    }

    @GetMapping
    public List<Driver> getAll() {
        return driverService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Driver getById(@PathVariable int id) {
        return driverService.getById(id);
    }

    @PutMapping("/{id}")
    public Driver edit (@PathVariable int id, @RequestBody @Valid Driver driver) {
        return driverService.edit(id, driver);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID (@PathVariable int id) {
        driverService.deleteById(id);
    }

    @DeleteMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearAll() {
        driverService.deleteAll();
    }

}
