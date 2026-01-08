package com.codejudge.api.repositories;

import com.codejudge.api.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Optional<Submission> findByUserId(Long userId);
}
