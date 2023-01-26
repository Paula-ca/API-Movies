package com.example.challengeBackEnd.service;

import com.example.challengeBackEnd.model.User;

import java.util.List;


public interface IUserService {
    User add(User user);
    List<User> list();
    User userByEmail(String email) ;
    Boolean existsUserByEmail(String email) ;
}
