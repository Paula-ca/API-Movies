package com.example.challengeBackEnd.repository;

import com.example.challengeBackEnd.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonajeRepository extends JpaRepository<Personaje,Long> {

}
