package com.example.driverscars.services.imp;

import com.example.driverscars.dao.AddressDAO;
import com.example.driverscars.dao.DriverDao;
import com.example.driverscars.entiti.Address;
import com.example.driverscars.entiti.Driver;
import com.example.driverscars.services.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class DriverService implements IDriverService {

    @Autowired
    DriverDao driverDao;
    @Autowired
    AddressDAO addressDAO;

    @Override
    public Driver insert(Driver driver) {
        return driverDao.save(driver);
    }

    @Override
    public Driver insert(Driver driver, int addressId) {
        final Address address = addressDAO.findById(addressId).orElseThrow(() ->
                new NullPointerException("No such Address with Id: " + addressId));
        driver.getAddresses().add(address);
        return driverDao.save(driver);
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.findAll();
    }

    @Override
    public List<Driver> getByAddress(int addressId) {
        return driverDao.getDriversByAddressId(addressId);
    }

    @Override
    public Driver getById(int id) {
        return driverDao
                .findById(id)
                .orElseThrow(() -> new NullPointerException("there is no Driver with Id: " + id));
    }

    @Override
    public Driver edit(int id, Driver driver) {
        if (driverDao.existsById(id)) {
            driver.setId(id);
            driverDao.saveAndFlush(driver);
            return driver;
        }else {
            throw new NullPointerException("No such Driver with Id: " + id);
        }
    }

    @Override
    public void deleteById(int id) {
        if (driverDao.existsById(id)) {
            driverDao.deleteById(id);
        }else {
                throw new NullPointerException("no such Driver with Id: " + id);
            }
    }

    @Override
    public void deleteAll() {
        driverDao.deleteAll();
    }



}
