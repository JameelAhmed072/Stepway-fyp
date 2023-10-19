package com.example.Stepway.Repository;


import com.example.Stepway.Domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRespository extends JpaRepository<Enrollment,Long> {
}
