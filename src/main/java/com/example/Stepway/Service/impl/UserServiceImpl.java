package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Role;
import com.example.Stepway.Domain.User;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.RoleRepository;
import com.example.Stepway.Repository.UserRepository;
import com.example.Stepway.Repository.UserSpecification;
import com.example.Stepway.Service.UserService;
import com.example.Stepway.dto.SearchCriteria;
import com.example.Stepway.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EntityManager entityManager;  // entity manager gives us criteria builder

//    public List<User> getUserWithFilters(String firstName,String lastName){
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
//        Root<User>  userRoot = cq.from(User.class);
//        List<Predicate> predicates = new ArrayList<>();
//        if(firstName != null){
//            predicates.add(criteriaBuilder.like(userRoot.get("firstName"),firstName));
//        }
//        if(lastName != null){
//            predicates.add(criteriaBuilder.like(userRoot.get("lastName"),firstName));
//        }
//
//        cq.where(predicates.toArray(new Predicate[predicates.size()]));
//        TypedQuery<User> query = entityManager.createQuery(cq);
//        return query.getResultList();
//    }


    public List<User> getSearchdUser(SearchCriteria searchCriteria){
        try {
            UserSpecification userSpecification = new UserSpecification(searchCriteria);
            List<User> users = userRepository.findAll(userSpecification);
            return users;
        }
        catch (Exception e){
            throw new RuntimeException("No Employee Exist "+e);
        }
    }
    @Override
    public List<UserDto> getAllUser() {

        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User userByEmail = userRepository.findByEmail(userDto.getEmail());

        if(userByEmail == null){
            try {
                Optional<Role> roles = Optional.ofNullable(roleRepository
                        .findByName(userDto.getRole())
                        .orElseThrow(() -> new RuntimeException("Role is incorrect")));

                Set<Role> rolesList = new HashSet<>();
                rolesList.add(roles.get());

                User user = User.builder()
                        .firstName(userDto.getFirstName())
                        .lastName(userDto.getLastName())
                        .password(userDto.getPassword())
                        .role(rolesList)
                        .phoneNumber(userDto.getPhoneNumber())
                        .email(userDto.getEmail())
                        .gender(userDto.getGender())
                        .build();
                User save = userRepository.save(user);
                return modelMapper.map(save,UserDto.class);

            }catch(Exception e){
                throw new RuntimeException("Some information is incorrect");
            }
        }else{
            throw new RuntimeException("Email is Already exist");
        }
    }

    @Override
    public UserDto getUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        User user = optionalUser.orElseThrow(()->new ResourceNotFound("User not found with the id : "+ id));

        return modelMapper.map(user,UserDto.class);
    }
    public Long countStudentsWithRoleStudent() {
        Long optionalUser = userRepository.countUsersWithRoleStudent();


        return optionalUser;
    }

    public Long countMaleStudents(){
        Long maleStudents = userRepository.countMaleStudents();
        return maleStudents;
    }
    public Long countFemaleStudents(){
        Long femlaeStudents = userRepository.countFemaleStudents();
        return femlaeStudents;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            try {
                Optional<Role> roles = Optional.ofNullable(roleRepository
                        .findByName(userDto.getRole())
                        .orElseThrow(() -> new RuntimeException("Role is incorrect")));

                Set<Role> rolesList = new HashSet<>();
                rolesList.add(roles.get());

                User user1 = User.builder()
                        .firstName(userDto.getFirstName())
                        .lastName(userDto.getLastName())
                        .email(userDto.getEmail())
                        .password(userDto.getPassword())
                        .role(rolesList)
                        .phoneNumber(userDto.getPhoneNumber())
                        .gender(userDto.getGender())
                        .build();
                User save = userRepository.save(user1);
                return modelMapper.map(save, UserDto.class);
            } catch (Exception e) {
                throw new RuntimeException("Some information is incorrect");
            }
        }
        throw new RuntimeException("User Not Found");
    }

    @Override
    public void deleteUser(Long id) {

        if(!userRepository.existsById(id)){
            throw new ResourceNotFound("User not found with Id : "+ id);
        }
        userRepository.deleteById(id);
    }

    public List<UserDto> findStudent(){

        List<User> users = userRepository.findUsersWithRoleStudent();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());

    }
    public List<UserDto> findTeachers(){

        List<User> users = userRepository.findUsersWithRoleTeacher();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());

    }




}
