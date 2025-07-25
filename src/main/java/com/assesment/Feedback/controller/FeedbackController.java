package com.assesment.Feedback.controller;
import com.assesment.Feedback.model.Feedback;
import com.assesment.Feedback.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService service;

    public FeedbackController(FeedbackService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@Valid @RequestBody Feedback feedback){
        return ResponseEntity.ok(service.saveFeedback(feedback));
    }


    @GetMapping
    public List<Feedback> getAllFeedback(){
        return service.getAllFeedback();
    }
}
