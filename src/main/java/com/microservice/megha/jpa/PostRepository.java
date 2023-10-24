package com.microservice.megha.jpa;

import com.microservice.megha.social.media.application.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
