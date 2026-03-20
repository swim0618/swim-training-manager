package com.example.swim_training_manager.form;

import com.example.swim_training_manager.entity.enumtype.IntensityType;
import com.example.swim_training_manager.entity.enumtype.StrokeType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TrainingSetForm {

    @NotNull
    private Integer orderNo;

    @NotNull
    private Integer distanceM;

    @NotNull
    private Integer reps;

    private Integer intervalSec;

    private StrokeType stroke;

    private IntensityType intensity;

    @Size(max = 1000)
    private String memo;

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