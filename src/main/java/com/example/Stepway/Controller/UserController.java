package com.example.Stepway.Controller;

import com.example.Stepway.Domain.User;
import com.example.Stepway.Service.impl.UserServiceImpl;
import com.example.Stepway.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;


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



}
