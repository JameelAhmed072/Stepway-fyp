package com.example.Stepway.Repository;


import com.example.Stepway.Domain.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository  extends JpaRepository<Certification,Long> {

}
