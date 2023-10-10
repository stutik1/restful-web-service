package com.microservice.megha.social.media.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
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
        User user = userDaoService.getById(id);
        if (user == null){
            throw new UserNotFoundException("id :" + id );
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User user){
       User savedUser = userDaoService.createUser(user);
       // return ResponseEntity.ok(user);
       // return  ResponseEntity.status(HttpStatus.CREATED).body(user);


        //TODO: aSK GURU
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
            return ResponseEntity.created(location).build() ;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id){
       userDaoService.deleteUserById(id);
    }
}
