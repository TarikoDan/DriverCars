package com.example.driverscars.entiti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    int id;
    @NotNull
    @Max(99999)
    int postCode;
    @NotBlank
    String city;
    String street;
    int houseNumber;

    @ManyToMany()
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @JoinTable(name = "DRIVER_ADDRESS"
        ,joinColumns = @JoinColumn(name = "address_id", nullable = false, updatable = false)
        ,inverseJoinColumns = @JoinColumn(name = "driver_id", nullable = false, updatable = false)
    )
    Set<Driver> drivers = new HashSet<>();




}
