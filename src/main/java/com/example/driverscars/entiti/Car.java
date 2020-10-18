package com.example.driverscars.entiti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String model;
    @Positive
    @Min(1900)
    private int year;

    @ManyToOne( fetch = FetchType.LAZY, targetEntity = Driver.class)
    @JoinColumn(name = "driver_id")
    Driver driver;

}
