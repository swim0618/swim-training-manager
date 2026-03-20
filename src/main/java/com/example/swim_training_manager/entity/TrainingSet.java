package com.example.swim_training_manager.entity;


import jakarta.persistence.*;
import com.example.swim_training_manager.entity.enumtype.StrokeType;
import com.example.swim_training_manager.entity.enumtype.IntensityType;

@Entity
public class TrainingSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) 
    private StrokeType stroke;

    @Enumerated(EnumType.STRING)   
    private IntensityType intensity;
}
