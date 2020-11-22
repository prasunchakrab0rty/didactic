package com.example.didactic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserManager {
    private static UserManager INSTANCE;
    private List<User> users;

    private UserManager() {
        users = new ArrayList<>();
    }

    public static UserManager getInstance() {
        if (INSTANCE == null) INSTANCE = new UserManager();
        return INSTANCE;
    }

    public Optional<User> findUser(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public boolean addUser(User user) {
        user.setId(UUID.randomUUID());
        users.add(user);
        return true;
    }

    public List<User> findUsers() {
        return users;
    }

    public boolean deleteUser(String email) {
        Optional<User> optionalUser = users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
        if (optionalUser.isPresent()) {
            users.removeIf(user -> user.getEmail().equals(email));
            return true;
        }
        return false;
    }
}
