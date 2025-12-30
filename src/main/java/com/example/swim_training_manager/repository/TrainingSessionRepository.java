package com.example.swim_training_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.swim_training_manager.entity.Training;

public interface TrainingSessionRepository
        extends JpaRepository<Training, Long> {
}
