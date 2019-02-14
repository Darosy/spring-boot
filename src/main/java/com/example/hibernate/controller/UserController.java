package com.example.hibernate.controller;

import com.example.hibernate.dto.UserDto;
import com.example.hibernate.dto.UserProfileDto;
import com.example.hibernate.model.UserModel;
import com.example.hibernate.model.UserProfileModel;
import com.example.hibernate.repository.UserRepository;
import com.example.hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Hibernate
    @GetMapping(value="/get", headers="Accept=application/json")
    public List<UserModel> getAllUser() {
        List<UserModel> listUser=userService.getUser();
        return listUser;
    }

    // JPA
    @GetMapping(value="/all", headers="Accept=application/json")
    public List<UserDto> getAllUsers() {
        List<UserModel> listUser = userRepository.findAll();
        List<UserDto> list = new ArrayList<>();
        for (UserModel userModel : listUser) {
            UserDto dto = new UserDto();
            UserProfileDto dtoProfile = new UserProfileDto(
                    userModel.getProfile().getId(), userModel.getProfile().getPhoneNumber(),
                    userModel.getProfile().getGender(), userModel.getProfile().getDateOfBirth(),
                    userModel.getProfile().getAddress1(), userModel.getProfile().getAddress2(),
                    userModel.getProfile().getStreet(), userModel.getProfile().getCity(),
                    userModel.getProfile().getState(), userModel.getProfile().getCountry(),
                    userModel.getProfile().getZipCode()
            );

            dto.setId(userModel.getId());
            dto.setEmail(userModel.getEmail());
            dto.setFirstName(userModel.getFirstName());
            dto.setLastName(userModel.getLastName());
            dto.setPassword(userModel.getPassword());
            dto.setProfile(dtoProfile);
            list.add(dto);
        }
        return list;
    }

    // Hibernate
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") Long id) {
        System.out.println("Fetching User with id " + id);
        UserModel user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserModel>(user, HttpStatus.OK);
    }

    // JPA
    @GetMapping(value = "/JPA/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserByIdJpa(@PathVariable("id") Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        UserModel userModel = user.get();
        UserDto dto = new UserDto();
        UserProfileDto dtoProfile = new UserProfileDto(
                userModel.getProfile().getId(), userModel.getProfile().getPhoneNumber(),
                userModel.getProfile().getGender(), userModel.getProfile().getDateOfBirth(),
                userModel.getProfile().getAddress1(), userModel.getProfile().getAddress2(),
                userModel.getProfile().getStreet(), userModel.getProfile().getCity(),
                userModel.getProfile().getState(), userModel.getProfile().getCountry(),
                userModel.getProfile().getZipCode()
        );
        dto.setId(userModel.getId());
        dto.setEmail(userModel.getEmail());
        dto.setFirstName(userModel.getFirstName());
        dto.setLastName(userModel.getLastName());
        dto.setPassword(userModel.getPassword());
        dto.setProfile(dtoProfile);
        return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
    }

    // JPA
    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Void> createUser(@RequestBody UserModel user, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+user.getFirstName());
        userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // Hibernate
    @PostMapping(value="/add",headers="Accept=application/json")
    public ResponseEntity<Void> addUser(@RequestBody UserModel userModel, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User "+userModel.getFirstName());

        UserModel user = new UserModel();
        user.setId(System.currentTimeMillis());
        user.setLastName(userModel.getLastName());
        user.setFirstName(userModel.getFirstName());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());

        UserProfileModel profile = userModel.getProfile();
        profile.setId(System.currentTimeMillis());

        user.setProfile(profile);
        profile.setUser(user);
        userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
