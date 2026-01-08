package com.codejudge.api.services;

import com.codejudge.api.models.Submission;

public interface ISubmissioService {
    Submission processSubmission(Submission submission);
}