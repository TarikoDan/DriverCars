package com.example.driverscars.dao;

import com.example.driverscars.entiti.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseDao extends JpaRepository<License, Integer> {
}
