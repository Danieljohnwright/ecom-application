package com.ecom.app;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private Long nextId = 1L;

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.fetchAllUsers();
        }

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user) {
        user.setId(nextId++);
        userService.addUser(user);
        return "User Added Successfully";
        }
    }
