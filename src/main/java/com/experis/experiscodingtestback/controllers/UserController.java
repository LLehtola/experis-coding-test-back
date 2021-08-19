package com.experis.experiscodingtestback.controllers;

import com.experis.experiscodingtestback.models.User;
import com.experis.experiscodingtestback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = ControllerHelpers.API_V1 + "/users")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(users, status);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(newUser, status);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        if (!userService.userExists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        User user = userService.findUser(id);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(user, status);
    }

    @GetMapping(value="/checkuser")
    public ResponseEntity <Boolean> checkUser(@RequestParam String email) {
        boolean users = userService.emailExists(email);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(users, status);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User newUser) {
        User returnUser = new User();
        HttpStatus status;
        if (id != newUser.getId()) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnUser, status);
        }
        if (userService.userExists(id)) {
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.CREATED;
        }
        returnUser = userService.saveUser(newUser);
        return new ResponseEntity<>(returnUser, status);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        if (!userService.userExists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        HttpStatus status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(null, status);
    }


}
