package com.ecom.app;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();


    public List<User> fetchAllUsers() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);

    }

    public Optional<User> fetchUser(Long id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst();

    }

    public boolean updateUser(long id, User updateUser){
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(
                        existingUser -> {
                            existingUser.setFirstName(updateUser.getFirstName());
                            existingUser.setLastName(updateUser.getLastName());
                            return true;
                        }).orElse(false);
    }


}

