package com.example.driverscars.controllers;

import com.example.driverscars.dto.response.AddressResponse;
import com.example.driverscars.entiti.Address;
import com.example.driverscars.services.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {

    private final IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse insert(@RequestBody @Valid Address address) {
        return addressService.insert(address);
    }

    @PostMapping("/driver/{driverId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse insertWithDriver(@RequestBody @Valid Address address,
                                    @PathVariable int driverId) {
        return addressService.insert(address, driverId);
    }

    @GetMapping
    public List<AddressResponse> getAll() {
        return addressService.getAll();
    }

    @GetMapping(value = "/{id}")
    public AddressResponse getById(@PathVariable int id) {
        return addressService.getById(id);
    }

    @PutMapping(value = "/{id}/driver/{driverId}")
    public AddressResponse setDriverToAddress(@PathVariable int id, @PathVariable int driverId) {
        return addressService.setDriverToAddress(id, driverId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID (@PathVariable int id) {
        addressService.deleteById(id);
    }

}
