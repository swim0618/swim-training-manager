package com.example.swim_training_manager.entity;

import jakarta.persistence.*;

@Entity
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
