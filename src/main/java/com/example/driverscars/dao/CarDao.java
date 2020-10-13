package com.example.driverscars.dao;

import com.example.driverscars.entiti.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car, Integer> {
}
