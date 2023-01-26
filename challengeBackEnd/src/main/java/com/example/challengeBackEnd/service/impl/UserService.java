package com.example.challengeBackEnd.service.impl;

import com.example.challengeBackEnd.repository.IUserRepository;
import com.example.challengeBackEnd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.challengeBackEnd.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    public User add(User user) {
        if(user.getNombre()!=null
                && user.getApellido() != null
                && user.getEmail() != null
                && user.getContrasenia() != null) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }



    public List<User> list() {
        return userRepository.findAll();
    }

    public User userByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
