package com.example.swim_training_manager.service;

import com.example.swim_training_manager.entity.TrainingSession;
import com.example.swim_training_manager.entity.TrainingSet;
import com.example.swim_training_manager.entity.User;
import com.example.swim_training_manager.entity.enumtype.UserRole;
import com.example.swim_training_manager.form.TrainingSessionForm;
import com.example.swim_training_manager.repository.TrainingSessionRepository;
import com.example.swim_training_manager.repository.TrainingSetRepository;
import com.example.swim_training_manager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;
    private final TrainingSetRepository trainingSetRepository;
    private final UserRepository userRepository;

    public TrainingSessionService(
            TrainingSessionRepository trainingSessionRepository,
            TrainingSetRepository trainingSetRepository,
            UserRepository userRepository
    ) {
        this.trainingSessionRepository = trainingSessionRepository;
        this.trainingSetRepository = trainingSetRepository;
        this.userRepository = userRepository;
    }

    public List<TrainingSession> findAllByLoginUser(String email) {
        User user = getOrCreateUser(email);
        return trainingSessionRepository.findByUserOrderByStartAtDesc(user);
    }

    public TrainingSession findById(String email, Long id) {
        User user = getOrCreateUser(email);
        return trainingSessionRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("練習記録が見つかりません"));
    }

    public List<TrainingSet> findSets(Long sessionId, String email) {
        TrainingSession session = findById(email, sessionId);
        return trainingSetRepository.findByTrainingSessionOrderByOrderNoAsc(session);
    }

    public TrainingSession create(String email, TrainingSessionForm form) {
        User user = getOrCreateUser(email);

        TrainingSession session = new TrainingSession();
        session.setUser(user);
        session.setStartAt(form.getStartAt());
        session.setEndAt(form.getEndAt());
        session.setTitle(form.getTitle());
        session.setNotes(form.getNotes());

        return trainingSessionRepository.save(session);
    }

    public void update(String email, Long id, TrainingSessionForm form) {
        TrainingSession session = findById(email, id);
        session.setStartAt(form.getStartAt());
        session.setEndAt(form.getEndAt());
        session.setTitle(form.getTitle());
        session.setNotes(form.getNotes());
    }

    public void delete(String email, Long id) {
        TrainingSession session = findById(email, id);
        trainingSessionRepository.delete(session);
    }

    public TrainingSessionForm toForm(TrainingSession session) {
        TrainingSessionForm form = new TrainingSessionForm();
        form.setStartAt(session.getStartAt());
        form.setEndAt(session.getEndAt());
        form.setTitle(session.getTitle());
        form.setNotes(session.getNotes());
        return form;
    }

    private User getOrCreateUser(String email) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail(email);
                    user.setRole(UserRole.USER);
                    return userRepository.save(user);
                });
    }
}