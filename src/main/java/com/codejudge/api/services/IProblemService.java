package com.codejudge.api.services;

import com.codejudge.api.models.Problem;

import java.util.List;
import java.util.Optional;

public interface IProblemService {
    List<Problem> getAllProblemsList();
    Optional<Problem> getProblemById(Long id);
    Problem createProblem(Problem problem);

}
