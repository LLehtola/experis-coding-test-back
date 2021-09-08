package com.experis.experiscodingtestback.services;

import com.experis.experiscodingtestback.models.User;
import com.experis.experiscodingtestback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        deleteUsersByExpiryDate();
        return userRepository.findAll();
    }

    public void deleteUsersByExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date expiryDate = cal.getTime();
        userRepository.deleteByAnswerdateBefore(expiryDate);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean userExists(long id) {
        return userRepository.existsById(id);
    }

    public boolean emailExists(String email) { return userRepository.existsUserByEmail(email);}




    public User findUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);
    }

    public boolean deleteUser(long id) {
        if (userExists(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public User patchUser(User user) {
        return userRepository.save(user);

    }
}
