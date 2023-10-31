package com.example.Stepway.Controller;


import com.example.Stepway.Service.impl.CertificationServiceImpl;
import com.example.Stepway.dto.CertificationDto;
import com.example.Stepway.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class CertificationController {

    @Autowired
    CertificationServiceImpl certificationServiceImpl;

    @PostMapping("/certification")
    public ResponseEntity<CertificationDto> addCertification(@Valid @RequestBody CertificationDto certificationDto){

        CertificationDto createCertification = certificationServiceImpl.createCertification(certificationDto);

        return ResponseEntity.ok(createCertification);
    }
    @GetMapping("/certification/{id}")
    public ResponseEntity<CertificationDto> getCertificationById(@Valid @PathVariable Long id){

        CertificationDto certificationDto = certificationServiceImpl.getCertificationById(id);

        return ResponseEntity.ok(certificationDto);
    }

    @GetMapping("/allCertifications")
    public ResponseEntity<List<CertificationDto>> getAllCertifications(){

        List<CertificationDto> certifications = certificationServiceImpl.getALlCertifications();

        return ResponseEntity.ok(certifications);
    }

    @PutMapping("/certification/{id}")
    public ResponseEntity<CertificationDto> updateCertification(@Valid @RequestBody CertificationDto certificationDto, @PathVariable Long id){

        CertificationDto certification = certificationServiceImpl.updateCertificationById(id,certificationDto);

        return ResponseEntity.ok(certification);
    }

    @DeleteMapping("/deleteCertification/{id}")
    public ResponseEntity<CertificationDto> deleteCertificationById(@PathVariable Long id){
        certificationServiceImpl.deleteCertificationById(id);
        return ResponseEntity.noContent().build();
    }
}
