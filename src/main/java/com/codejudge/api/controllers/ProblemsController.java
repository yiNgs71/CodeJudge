package com.codejudge.api.controllers;

import com.codejudge.api.models.Problem;
import com.codejudge.api.services.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/problems")
public class ProblemsController {
    @Autowired
    private IProblemService problemService;

    @GetMapping
    public ResponseEntity<List<Problem>> getAll() {
        List<Problem> problems = problemService.getAllProblemsList();
        return ResponseEntity.ok(problems);
    }

    @PostMapping("/create")
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem){
        Problem newProblem = problemService.createProblem(problem);
        return new ResponseEntity<>(newProblem, HttpStatus.CREATED);
    }
}
