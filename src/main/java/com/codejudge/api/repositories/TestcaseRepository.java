package com.codejudge.api.repositories;

import com.codejudge.api.models.Testcase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestcaseRepository extends JpaRepository<Testcase, Long> {
}
