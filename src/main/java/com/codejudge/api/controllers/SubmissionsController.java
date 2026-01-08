package com.codejudge.api.controllers;

import com.codejudge.api.models.Submission;
import com.codejudge.api.services.ISubmissioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/submission")
public class SubmissionsController {
    @Autowired
    private ISubmissioService submissionService;

    @PostMapping("/run")
    public ResponseEntity<Submission> runSubmission(@RequestBody Submission submision){
        Submission newsubmission = submissionService.processSubmission(submision);
        return new ResponseEntity<>(newsubmission, HttpStatus.CREATED);
    }
}
