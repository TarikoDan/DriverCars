package com.example.driverscars.services.imp;

import com.example.driverscars.dao.UserDAO;
import com.example.driverscars.dto.request.UserRequest;
import com.example.driverscars.dto.response.UserResponse;
import com.example.driverscars.entiti.User;
import com.example.driverscars.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;  /* цей бін описаний в SecurityConfig*/

    @Override
    public UserResponse save(UserRequest user) {
        if (userDAO.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        /* перевірити чи починається роль з "ROLE_" */
//        if ((user.getRole() == null) || !user.getRole().startsWith("ROLE_")) {
//            throw new RuntimeException("User role should start with 'ROLE_'");
//        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userDB = userRequestToUser(user);
        userDAO.saveAndFlush(userDB);      /*saving new User to DB*/

        return userToUserResp(userDB);
    }

    @Override
    public List<UserResponse> getAll() {
        List<UserResponse> users = new ArrayList<>();
        userDAO.findAll()
                .forEach(user -> users.add(
                        userToUserResp(user)
                ));
        return users;
    }

    @Override
    public UserResponse getByEmail(String email) {
        return userToUserResp(
                userDAO.findUserByEmail(email).orElseThrow(() ->
                        new NullPointerException("no such User with email: " + email))
        );
    }

    @Override
    public void remove(String email) {
        if (userDAO.existsByEmail(email)) {
            userDAO.removeUserByEmail(email);
        } else {
            throw new NullPointerException("no such User with email: " + email);
        }
    }

    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }

    private UserResponse userToUserResp(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surName(user.getSurName())
                .email(user.getEmail())
                .dayOfBirth(user.getBirthday().getDayOfWeek())
                .build();
    }

    private User userRequestToUser(UserRequest user) {
        String birthDay = user.getBirthDay();
        if (!birthDay.matches("[1-2][0-9]{3}-[0-1][0-9]-[0-9]{2}")) {
            throw new DateTimeException("Incorrect Date format");
        };
        LocalDate birthDate = LocalDate.parse(user.getBirthDay());

        return User.builder()    /*creating new User*/
                .name(user.getName())
                .surName(user.getSurName())
                .email(user.getEmail())
                .password(user.getPassword())
//                .role(user.getRole())
                .birthday(birthDate)
                .build();


    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.getUserByEmail(s);
    }
}
