package com.example.Stepway.Repository;

import com.example.Stepway.Domain.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Long> {
}
