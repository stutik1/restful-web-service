package com.microservice.megha.jpa;

import com.microservice.megha.social.media.application.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
