package com.example.driverscars.controllers;

import com.example.driverscars.dto.request.AuthRequest;
import com.example.driverscars.dto.request.UserRequest;
import com.example.driverscars.dto.response.AuthResponse;
import com.example.driverscars.dto.response.UserResponse;
import com.example.driverscars.services.IUserService;
import com.example.driverscars.services.imp.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register (@RequestBody @Valid UserRequest user) {
        return userService.save(user);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse generateJWT (@RequestBody @Valid AuthRequest authRequest) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequest.getEmail(),authRequest.getPassword()
                        ));
        return new AuthResponse(jwtService.generateToken(authRequest.getEmail()));
    }

    @GetMapping
    public List<UserResponse> getAll(){return userService.getAll();}

    @GetMapping("/{email}")
    public UserResponse getByEmail (@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable String email ) {
        userService.getByEmail(email);
    }

}
