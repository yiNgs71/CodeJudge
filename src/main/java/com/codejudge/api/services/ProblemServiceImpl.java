package com.codejudge.api.services;

import com.codejudge.api.models.Problem;
import com.codejudge.api.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements IProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public List<Problem> getAllProblemsList() {
        return problemRepository.findAll();
    }

    @Override
    public Optional<Problem> getProblemById(Long id) {
        return problemRepository.findById(id);
    }

    @Override
    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }
}
