package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exceptions.IncorrectPasswordException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public User signUp(User user) {

        try {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        catch (Exception ex) {
            throw ex;
        }
        return user;
    }

    public String login(User user) throws UserNotFoundException, IncorrectPasswordException {

        Optional<User> fetchedUser = userRepository.findByusername(user.getUsername());

        if(fetchedUser.isEmpty())
            throw new UserNotFoundException("User not found" + user.getUsername());
        else if(encoder.matches(user.getPassword(), fetchedUser.get().getPassword()))
            return "User successfully logged in";

        throw new IncorrectPasswordException("Incorrect Credentials");
    }

    public List<User> getUsersWithInitials(String initial){
        return userRepository.findByUserNameStartsWith(initial);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
