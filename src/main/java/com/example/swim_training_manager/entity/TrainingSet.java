package com.example.swim_training_manager.entity;

import com.example.swim_training_manager.entity.enumtype.IntensityType;
import com.example.swim_training_manager.entity.enumtype.StrokeType;
import jakarta.persistence.*;

@Entity
@Table(name = "sets")
public class TrainingSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_session_id", nullable = false)
    private TrainingSession trainingSession;

    @Column(nullable = false)
    private Integer orderNo;

    @Column(nullable = false)
    private Integer distanceM;

    @Column(nullable = false)
    private Integer reps;

    private Integer intervalSec;

    @Enumerated(EnumType.STRING)
    private StrokeType stroke;

    @Enumerated(EnumType.STRING)
    private IntensityType intensity;

    @Column(columnDefinition = "text")
    private String memo;

    public Long getId() {
        return id;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public Integer getDistanceM() {
        return distanceM;
    }

    public Integer getReps() {
        return reps;
    }

    public Integer getIntervalSec() {
        return intervalSec;
    }

    public StrokeType getStroke() {
        return stroke;
    }

    public IntensityType getIntensity() {
        return intensity;
    }

    public String getMemo() {
        return memo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public void setDistanceM(Integer distanceM) {
        this.distanceM = distanceM;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public void setIntervalSec(Integer intervalSec) {
        this.intervalSec = intervalSec;
    }

    public void setStroke(StrokeType stroke) {
        this.stroke = stroke;
    }

    public void setIntensity(IntensityType intensity) {
        this.intensity = intensity;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}