package com.assesment.Feedback.service;

import com.assesment.Feedback.model.Feedback;
import com.assesment.Feedback.repo.FeedbackRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepo repository;

    public FeedbackService(FeedbackRepo repository) {
        this.repository = repository;
    }

    public Feedback saveFeedback(Feedback feedback) {
        if (feedback == null) {
            throw new IllegalArgumentException("Feedback cannot be null.");
        }
        return repository.save(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return repository.findAll();
    }
}
