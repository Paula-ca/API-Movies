package com.example.challengeBackEnd.repository;

import com.example.challengeBackEnd.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero,Long> {
}
