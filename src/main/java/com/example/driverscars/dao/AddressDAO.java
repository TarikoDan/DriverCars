package com.example.driverscars.dao;

import com.example.driverscars.entiti.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDAO extends JpaRepository<Address, Integer> {
}
