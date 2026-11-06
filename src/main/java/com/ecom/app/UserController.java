package com.ecom.app;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private Long nextId = 1L;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> createUser (@RequestBody User user){
        user.setId(nextId++);
        userService.addUser(user);
        return ResponseEntity.ok("User Added Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {

             return userService.fetchUser(id)
                     .map(ResponseEntity :: ok)
                     .orElse(ResponseEntity.notFound().build());
        }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser) {

        boolean updated = userService.updateUser(id, updatedUser);

        if (updated) {
            return ResponseEntity.ok("User Updated Successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    }

