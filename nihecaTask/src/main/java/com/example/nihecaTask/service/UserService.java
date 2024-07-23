package com.example.nihecaTask.service;

import com.example.nihecaTask.dtos.UserDto;
import com.example.nihecaTask.entities.User;
import com.example.nihecaTask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        if(userRepository.count() == 0) {
            return null;
        }
        else{
            return userRepository.findAll();
        }
    }

    public Optional<User> getUserById(Long id) {
        if(userRepository.existsById(id)) {
            return userRepository.findById(id);
        }
        else{
            return Optional.empty();
        }
    }
}
