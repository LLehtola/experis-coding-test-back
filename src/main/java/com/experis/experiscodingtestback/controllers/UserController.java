package com.experis.experiscodingtestback.controllers;

import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.models.User;
import com.experis.experiscodingtestback.repositories.UserRepository;
import com.experis.experiscodingtestback.services.RecaptchaService;
import com.experis.experiscodingtestback.services.UserService;
import com.google.api.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = ControllerHelpers.API_V1 + "/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecaptchaService captchaService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(users, status);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User addedUser = userService.createUser(user);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyRecaptcha(@RequestParam String recaptchaResponse,
                                    HttpServletRequest request){
        String ip = request.getRemoteAddr();
        boolean captchaVerifiedSuccessfully =
                captchaService.verifyRecaptcha(ip, recaptchaResponse);
        if (!captchaVerifiedSuccessfully || recaptchaResponse == null) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        if (!userService.userExists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        User user = userService.findUser(id);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(user, status);
    }

    @GetMapping("/checkuser")
    public ResponseEntity <Boolean> checkUser(@RequestParam String email) {
        boolean users = userService.emailExists(email);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(users, status);
    }


    @PutMapping("{id}")
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

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable long id, @RequestBody User contactedUpdate) {
        User returnUser = new User();
        User user = userRepository.findById(id).get();
        user.setHasbeencontacted(contactedUpdate.isHasbeencontacted());
        returnUser = userService.patchUser(user);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnUser, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        if (!userService.userExists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        HttpStatus status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(null, status);
    }

}
