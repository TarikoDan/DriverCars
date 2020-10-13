package com.example.driverscars.entiti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotBlank
    String name;
    @Email
    String email;

    @OneToOne
    @JsonIgnore
    License license;

    @OneToMany(targetEntity = Car.class)
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    List<Car> cars;

}
