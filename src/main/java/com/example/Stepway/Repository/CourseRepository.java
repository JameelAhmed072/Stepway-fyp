package com.example.Stepway.Repository;

import com.example.Stepway.Domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query(value = "select sum(c.price) from user u " +
            "inner join user_roles ur on ur.user_id = u.id " +
            "inner join role r on ur.role_id = r.id " +
            "inner join enrollment e on u.id = e.user_id " +
            "inner join course c on e.course_id = c.id  where r.name = 'ROLE_STUDENT'",nativeQuery = true)
    public Long totalEarning();


    @Query(value = "SELECT * FROM course c where c.course_name LIKE  %?% ", nativeQuery = true)
    public List<Course> searchByFirstLetter(String c);


    @Query(value = "SELECT * " +
            "FROM course c " +
            "INNER JOIN enrollment e ON c.id = e.course_id " +
            "INNER JOIN user u ON e.user_id = u.id " +
            "WHERE u.id = ?", nativeQuery = true)
    public List<Course> getAllEnrolledCourses(Long id);



}
