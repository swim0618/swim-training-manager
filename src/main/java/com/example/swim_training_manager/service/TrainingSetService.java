package com.example.swim_training_manager.service;

import com.example.swim_training_manager.entity.TrainingSession;
import com.example.swim_training_manager.entity.TrainingSet;
import com.example.swim_training_manager.form.TrainingSetForm;
import com.example.swim_training_manager.repository.TrainingSetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainingSetService {

    private final TrainingSetRepository trainingSetRepository;
    private final TrainingSessionService trainingSessionService;

    public TrainingSetService(
            TrainingSetRepository trainingSetRepository,
            TrainingSessionService trainingSessionService
    ) {
        this.trainingSetRepository = trainingSetRepository;
        this.trainingSessionService = trainingSessionService;
    }

    public TrainingSet create(String email, Long sessionId, TrainingSetForm form) {
        TrainingSession session = trainingSessionService.findById(email, sessionId);

        TrainingSet trainingSet = new TrainingSet();
        trainingSet.setTrainingSession(session);
        trainingSet.setOrderNo(form.getOrderNo());
        trainingSet.setDistanceM(form.getDistanceM());
        trainingSet.setReps(form.getReps());
        trainingSet.setIntervalSec(form.getIntervalSec());
        trainingSet.setStroke(form.getStroke());
        trainingSet.setIntensity(form.getIntensity());
        trainingSet.setMemo(form.getMemo());

        return trainingSetRepository.save(trainingSet);
    }

    public TrainingSet findById(String email, Long setId) {
        TrainingSet trainingSet = trainingSetRepository.findById(setId)
                .orElseThrow(() -> new IllegalArgumentException("セットが見つかりません"));

        String ownerEmail = trainingSet.getTrainingSession().getUser().getEmail();
        if (!ownerEmail.equals(email)) {
            throw new IllegalArgumentException("このセットにアクセスできません");
        }

        return trainingSet;
    }

    public void update(String email, Long setId, TrainingSetForm form) {
        TrainingSet trainingSet = findById(email, setId);

        trainingSet.setOrderNo(form.getOrderNo());
        trainingSet.setDistanceM(form.getDistanceM());
        trainingSet.setReps(form.getReps());
        trainingSet.setIntervalSec(form.getIntervalSec());
        trainingSet.setStroke(form.getStroke());
        trainingSet.setIntensity(form.getIntensity());
        trainingSet.setMemo(form.getMemo());
    }

    public void delete(String email, Long setId) {
        TrainingSet trainingSet = findById(email, setId);
        trainingSetRepository.delete(trainingSet);
    }

    public TrainingSetForm toForm(TrainingSet trainingSet) {
        TrainingSetForm form = new TrainingSetForm();
        form.setOrderNo(trainingSet.getOrderNo());
        form.setDistanceM(trainingSet.getDistanceM());
        form.setReps(trainingSet.getReps());
        form.setIntervalSec(trainingSet.getIntervalSec());
        form.setStroke(trainingSet.getStroke());
        form.setIntensity(trainingSet.getIntensity());
        form.setMemo(trainingSet.getMemo());
        return form;
    }
}