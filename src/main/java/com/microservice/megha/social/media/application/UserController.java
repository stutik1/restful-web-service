package com.microservice.megha.social.media.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService){
        this.userDaoService =userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findall();
    }

    @GetMapping("/user/{id}")
    public User retrieveUser(@PathVariable int id){
        return userDaoService.findById(id);
    }

    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable int id){
        return userDaoService.getById(id);
    }
}
