package com.example.driverscars.dao;

import com.example.driverscars.entiti.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDao extends JpaRepository<Driver, Integer> {
}
