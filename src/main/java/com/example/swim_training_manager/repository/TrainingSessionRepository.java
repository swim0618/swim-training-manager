package com.example.swim_training_manager.repository;

import com.example.swim_training_manager.entity.TrainingSession;
import com.example.swim_training_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
    List<TrainingSession> findByUserOrderByStartAtDesc(User user);
    Optional<TrainingSession> findByIdAndUser(Long id, User user);
}