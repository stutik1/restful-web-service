package com.microservice.megha.jpa;

import com.microservice.megha.social.media.application.User;
import com.microservice.megha.social.media.application.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/user/{id}")
    public Optional<User> retrieveUser(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id :" + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        // return ResponseEntity.ok(user);
        // return  ResponseEntity.status(HttpStatus.CREATED).body(user);


        //TODO: aSK GURU
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/jpa/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveUserPostById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id :" + id);
        }
        return user.get().getPost();
    }

    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> CreateUserPostById(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id :" + id);
            post.setUser(user.get());
            Post savePost = postRepository.save(post);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/jpa/{id}")
                    .buildAndExpand(savePost.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
    }

}

