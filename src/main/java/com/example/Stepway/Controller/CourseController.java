package com.example.Stepway.Controller;


import com.example.Stepway.Domain.Course;
import com.example.Stepway.Service.impl.CourseServiceImpl;
import com.example.Stepway.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @PostMapping("/course")
    public ResponseEntity<CourseDto> addCourse(@Valid @RequestBody CourseDto courseDto){

        CourseDto createCourse = courseService.createCourse(courseDto);

        return ResponseEntity.ok(createCourse);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CourseDto> getCourseById(@Valid @PathVariable Long id){

        CourseDto courseDto = courseService.getCourseById(id);

        return ResponseEntity.ok(courseDto);
    }
    @GetMapping("/allCourses")
    public ResponseEntity<List<CourseDto>> getAllCourses(){

        List<CourseDto> courses = courseService.getALlCourses();

        return ResponseEntity.ok(courses);
    }
    @PutMapping("/course/{id}")
    public ResponseEntity<CourseDto> updateCourse(@Valid @RequestBody CourseDto courseDto, @PathVariable Long id){

        CourseDto course = courseService.updateCourseById(id,courseDto);

        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<CourseDto> deleteCourseById(@PathVariable Long id){
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/earning")
    public ResponseEntity<Long> totalEarning(){
        Long totEarning = courseService.totalEarning();

        return ResponseEntity.ok(totEarning);
    }


    @GetMapping("/courses/{name}")
    public ResponseEntity<List<Course>> searchByFirstLetter(@PathVariable("name") String name){

        List<Course> c = courseService.searchByFirstLetter(name);
        return ResponseEntity.ok(c);
    }
}
