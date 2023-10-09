package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.User;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.UserRepository;
import com.example.Stepway.Service.UserService;
import com.example.Stepway.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<UserDto> getAllUser() {

        List<User> users = userRepository.findAll();


        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = modelMapper.map(userDto,User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        User user = optionalUser.orElseThrow(()->new ResourceNotFound("User not found with the id : "+ id));

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFound("User not Found with the id :"+ id));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setPhoneNumber(userDto.getPhoneNumber());

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser,UserDto.class);

    }

    @Override
    public void deleteUser(Long id) {

        if(!userRepository.existsById(id)){
            throw new ResourceNotFound("User not found with Id : "+ id);
        }

        userRepository.deleteById(id);


    }
}
