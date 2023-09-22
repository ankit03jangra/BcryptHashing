package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exceptions.IncorrectPasswordException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.service.UserLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserLoginService userLoginService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody @Valid User user){

        return new ResponseEntity<>(userLoginService.signUp(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws UserNotFoundException, IncorrectPasswordException {
        return userLoginService.login(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userLoginService.getAllUsers();
    }

    @GetMapping("/getAllUsersWith")
    public List<User> getAllUsersWith(@RequestParam String initial){
        return userLoginService.getUsersWithInitials(initial);
    }

}
