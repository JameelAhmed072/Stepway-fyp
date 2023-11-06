package com.example.Stepway.Repository;

import com.example.Stepway.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    User findByEmail(String email);

    @Query(value = "SELECT u.* FROM User u " +
            "INNER JOIN user_roles ur ON u.id = ur.user_id " +
            "INNER JOIN Role r ON ur.role_id = r.id " +
            "WHERE r.name = 'ROLE_STUDENT'",
            nativeQuery = true)
    List<User> findUsersWithRoleStudent();
    @Query(value = "SELECT u.* FROM User u " +
            "INNER JOIN user_roles ur ON u.id = ur.user_id " +
            "INNER JOIN Role r ON ur.role_id = r.id " +
            "WHERE r.name = 'ROLE_TEACHER'",
            nativeQuery = true)
    List<User> findUsersWithRoleTeacher();

    @Query(value = "SELECT COUNT(u.id) FROM User u " +
            "INNER JOIN user_roles ur ON u.id = ur.user_id " +
            "INNER JOIN Role r ON ur.role_id = r.id " +
            "WHERE r.name = 'ROLE_STUDENT'",
            nativeQuery = true)
    Long countUsersWithRoleStudent();
//    List<User> getUserWithFilters(String firstName,String lastName);

    @Query(value = "select count(u.id) from User u " +
            "inner join user_roles ur on u.id = ur.user_id " +
            "inner join Role r on ur.role_id = r.id " +
            "where r.name = 'ROLE_STUDENT' and u.gender = 'Male'", nativeQuery = true)
    Long countMaleStudents();
    @Query(value = "select count(u.id) from User u " +
            "inner join user_roles ur on u.id = ur.user_id " +
            "inner join Role r on ur.role_id = r.id " +
            "where r.name = 'ROLE_STUDENT' and u.gender = 'Female'", nativeQuery = true)
    Long countFemaleStudents();


    @Query(value = "SELECT FIRST_NAME FROM USER WHERE ID = ?" , nativeQuery = true)
    public String getLoginName(Long id);


}
