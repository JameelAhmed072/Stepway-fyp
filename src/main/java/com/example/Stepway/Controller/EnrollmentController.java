package com.example.Stepway.Controller;

import com.example.Stepway.Service.impl.EnrollmentServiceImpl;
import com.example.Stepway.dto.EnrollmentDto;
import com.example.Stepway.dto.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class EnrollmentController {

    @Autowired
    EnrollmentServiceImpl enrollmentServiceImpl;

    @PostMapping("/enrollment")
    public ResponseEntity<EnrollmentDto> addEnrollment(@Valid @RequestBody EnrollmentDto enrollmentDto){

        EnrollmentDto createEnrollment = enrollmentServiceImpl.createEnrollment(enrollmentDto);

        return ResponseEntity.ok(createEnrollment);
    }
    @GetMapping("/enrollment/{id}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@Valid @PathVariable Long id){

        EnrollmentDto enrollmentDto = enrollmentServiceImpl.getEnrollmentById(id);

        return ResponseEntity.ok(enrollmentDto);
    }
    @GetMapping("/allEnrollments")
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollment(){

        List<EnrollmentDto> enrollments = enrollmentServiceImpl.getALlEnrollments();

        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/enrollment/{id}")
    public ResponseEntity<EnrollmentDto> updateEnrollment(@Valid @RequestBody EnrollmentDto enrollmentDto, @PathVariable Long id){

        EnrollmentDto enrollment = enrollmentServiceImpl.updateEnrollmentById(id,enrollmentDto);

        return ResponseEntity.ok(enrollment);
    }

    @DeleteMapping("/deleteEnrollment/{id}")
    public ResponseEntity<EnrollmentDto> deleteEnrollmentById(@PathVariable Long id){
        enrollmentServiceImpl.deleteEnrollmentById(id);
        return ResponseEntity.noContent().build();
    }
}
