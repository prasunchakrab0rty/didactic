package com.example.didactic.controller;

import com.example.didactic.model.User;
import com.example.didactic.model.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (UserManager.getInstance().addUser(user))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<?> findUsers(@RequestParam(required = false) String email) {
        if (email == null || email.equals("")) {
            List<User> users = UserManager.getInstance().findUsers();
            if (users != null && !users.isEmpty())
                return new ResponseEntity<>(users, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Optional<User> user = UserManager.getInstance().findUser(email);
            if (user.isPresent()) return new ResponseEntity<>(user.get(), HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(produces = {"application/json"})
    public ResponseEntity<?> deleteUser(@RequestParam String email) {
        if (UserManager.getInstance().deleteUser(email))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
