package com.example.driverscars.dao;

import com.example.driverscars.entiti.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverDao extends JpaRepository<Driver, Integer> {
    @Query("SELECT d FROM Driver d JOIN FETCH d.addresses a WHERE a.id=:addressId")
    List<Driver> getDriversByAddressId(int addressId);
}
