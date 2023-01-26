package com.example.challengeBackEnd.service;

import com.example.challengeBackEnd.model.Personaje;

import java.util.List;

public interface IPersonajeService {
    Personaje crear(Personaje p);
    Personaje getByName(String name);
    List<Personaje> getByAge(int age);
    List<Personaje> getByMovie(Long idMovie);
    Personaje modificar(Personaje p);
    String eliminar(Long id) throws Exception;
    List<Personaje> listar();
}
