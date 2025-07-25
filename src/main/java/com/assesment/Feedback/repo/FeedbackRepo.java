package com.assesment.Feedback.repo;

import com.assesment.Feedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

}