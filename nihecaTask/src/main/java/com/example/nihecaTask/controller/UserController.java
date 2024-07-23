package com.example.nihecaTask.controller;

import com.example.nihecaTask.dtos.UserDto;
import com.example.nihecaTask.entities.User;
import com.example.nihecaTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestParam String username, @RequestParam String password) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userService.createUser(userDto);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getUsers() {

        if(userService.getAllUsers() != null){
            return ResponseEntity.ok(iterableUserToIterableUserDto(userService.getAllUsers()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        if(!userService.getUserById(id).isEmpty()){

            UserDto userDto = new UserDto();
            userDto.setUsername(userService.getUserById(id).get().getUserName());
            userDto.setPassword(userService.getUserById(id).get().getPassword());

            return ResponseEntity.ok(userDto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    private Iterable<UserDto> iterableUserToIterableUserDto(Iterable<User> users) {

        ArrayList<UserDto> userDtosDtos = new ArrayList<>();

        users.forEach(user -> userDtosDtos.add(new UserDto(user.getUserName(), user.getPassword())));

        return userDtosDtos;

    }
}
