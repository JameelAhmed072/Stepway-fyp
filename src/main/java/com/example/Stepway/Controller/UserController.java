package com.example.Stepway.Controller;

import com.example.Stepway.Domain.Course;
import com.example.Stepway.Domain.User;
import com.example.Stepway.Service.impl.UserServiceImpl;
import com.example.Stepway.dto.CustomUserDetails;
import com.example.Stepway.dto.SearchCriteria;
import com.example.Stepway.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

//    @GetMapping("/")
//    public List<User> getUserWithFilters(@RequestParam(required = false) String firstName,
//                                         @RequestParam(required = false) String lastName
//                                         ){
//        return userServiceImpl.getUserWithFilters(firstName,lastName);
//    }

    @PostMapping("/user/search")
    public ResponseEntity<List<User>> filterUser(@RequestBody SearchCriteria searchCriteria){
        List<User> users = userServiceImpl.getSearchdUser(searchCriteria);
        return ResponseEntity.ok(users);
    }


    @PostMapping("/user")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto){

        UserDto createUser = userServiceImpl.createUser(userDto);

        return ResponseEntity.ok(createUser);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@Valid @PathVariable Long id){

        UserDto userDto = userServiceImpl.getUserById(id);

        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUser(){

        List<UserDto> users = userServiceImpl.getAllUser();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long id){

        UserDto user = userServiceImpl.updateUser(id,userDto);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Long id){
        userServiceImpl.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/students")
    public ResponseEntity<List<UserDto>> findUserByRoleStudent(){

        List<UserDto> students = userServiceImpl.findStudent();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/teachers")
    public ResponseEntity<List<UserDto>> findUserByRoleTeacher(){

        List<UserDto> students = userServiceImpl.findTeachers();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> countStudentsWithRoleStudent() {

        Long total = userServiceImpl.countStudentsWithRoleStudent();
        return ResponseEntity.ok(total);
    }
    @GetMapping("/maleStudents")
    public ResponseEntity<Long> countTotalMaleStudents(){

        Long maleStudents = userServiceImpl.countMaleStudents();
        return ResponseEntity.ok(maleStudents);
    }
    @GetMapping("/femaleStudents")
    public ResponseEntity<Long> countTotalFemaleStudents(){

        Long femlaeStudents = userServiceImpl.countFemaleStudents();
        return ResponseEntity.ok(femlaeStudents);
    }

    @GetMapping("/username")
    public ResponseEntity<String> getUserName() {
        // Get the currently logged-in user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long currentUserId = userDetails.getUserId();

                String userName = userServiceImpl.getUserByName(currentUserId);
                return ResponseEntity.ok(userName);

        } else {
            throw new RuntimeException("User with this Id does not found");
        }
    }


}
