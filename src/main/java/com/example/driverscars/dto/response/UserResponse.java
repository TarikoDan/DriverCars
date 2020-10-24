package com.example.driverscars.dto.response;

import lombok.*;

import java.time.DayOfWeek;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String surName;
    @NonNull
    private String email;
    private DayOfWeek dayOfBirth;
}
