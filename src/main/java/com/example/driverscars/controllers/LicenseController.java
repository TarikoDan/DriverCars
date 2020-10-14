package com.example.driverscars.controllers;

import com.example.driverscars.dto.response.LicenseResponse;
import com.example.driverscars.entiti.License;
import com.example.driverscars.services.ILicenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/licenses")
@CrossOrigin(origins = "http://localhost:4200")
public class LicenseController {

    private final ILicenseService licenseService;

    @Autowired
    public LicenseController(ILicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @PostMapping("/driver/{driverId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LicenseResponse insert(@RequestBody @Valid License license, @PathVariable int driverId) {
        return licenseService.insert(license, driverId);
    }

    @GetMapping
    public List<LicenseResponse> getAll() {
        return licenseService.getAll();
    }

    @GetMapping(value = "/{id}")
    public LicenseResponse getById(@PathVariable int id) {
        return licenseService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID (@PathVariable int id) {
        licenseService.deleteById(id);
    }

}
