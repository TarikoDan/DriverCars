package com.example.driverscars.entiti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @Email
    private String email;

    @OneToOne(optional = true,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "license_id")
    @JsonIgnore
    License license;

    @OneToMany(mappedBy = "driver", targetEntity = Car.class)
    @JsonIgnore
    Set<Car> cars = new HashSet<>();

    @ManyToMany()
    @JsonIgnore
    @JoinTable(name = "DRIVER_ADDRESS"
            ,joinColumns = @JoinColumn(name = "driver_id", nullable = false, updatable = false)
            ,inverseJoinColumns = @JoinColumn(name = "address_id", nullable = false, updatable = false)
    )
    Set<Address> addresses = new HashSet<>();

}
