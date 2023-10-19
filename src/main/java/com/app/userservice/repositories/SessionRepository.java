package com.app.userservice.repositories;

import com.app.userservice.models.entities.Session;
import com.app.userservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUser(User user);

    Optional<Session> findByTokenAndUserId(String token, Long userId);
}
