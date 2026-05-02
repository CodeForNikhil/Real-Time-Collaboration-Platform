package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Long>{

	Optional<UserSession> findByUserId(Long userId);
    List<UserSession> findByOnlineTrueAndUserIdNot(Long userId);
    List<UserSession> findByOnlineTrue();

}
