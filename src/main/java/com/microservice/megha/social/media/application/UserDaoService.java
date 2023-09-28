package com.microservice.megha.social.media.application;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"Megha", LocalDate.now().minusYears(25)));
        users.add(new User(2,"Guru",LocalDate.now().minusYears(30)));
        users.add(new User(3,"Stuti",LocalDate.now().minusYears(22)));
    }

    public List<User> findall(){
        return users;
    }

    public User findById(int id) {
        for (User user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null; // User not found
    }

    public User getById(int id){
        Predicate<? super User> predicate = users -> Objects.equals(users.getId(), id);
        return users.stream().filter(predicate).findFirst().get();
    }

}
