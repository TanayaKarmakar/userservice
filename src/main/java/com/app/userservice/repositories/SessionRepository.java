package com.app.userservice.repositories;

import com.app.userservice.models.entities.Session;
import com.app.userservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    List<Session> findByUser(User user);
}
