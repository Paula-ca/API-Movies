package com.example.challengeBackEnd.service;

import com.example.challengeBackEnd.model.Genero;
import com.example.challengeBackEnd.model.GeneroDto;

import java.util.List;

public interface IGeneroService {
    Genero crear(Genero g);
    Genero modificar(Genero g);
    String eliminar(Long id) throws Exception;
    List<GeneroDto> listar();
}
