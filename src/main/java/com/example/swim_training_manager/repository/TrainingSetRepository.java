package com.example.swim_training_manager.repository;

import com.example.swim_training_manager.entity.TrainingSession;
import com.example.swim_training_manager.entity.TrainingSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingSetRepository extends JpaRepository<TrainingSet, Long> {
    List<TrainingSet> findByTrainingSessionOrderByOrderNoAsc(TrainingSession trainingSession);
}