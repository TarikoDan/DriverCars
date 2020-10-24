package com.example.driverscars.services;

import com.example.driverscars.dto.request.UserRequest;
import com.example.driverscars.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse save(UserRequest user);

    List<UserResponse> getAll ();

    UserResponse getByEmail(String email);

    void remove(String nickName);

    void deleteAll();

}
