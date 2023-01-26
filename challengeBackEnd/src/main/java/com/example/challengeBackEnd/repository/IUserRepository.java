package com.example.challengeBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.challengeBackEnd.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    Boolean existsByEmail(String email);
}
