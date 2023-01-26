package com.example.challengeBackEnd.service;


import com.example.challengeBackEnd.model.PeliculaoSerie;
import com.example.challengeBackEnd.model.PeliculaoSerieDto;

import java.util.List;
import java.util.Set;

public interface IPeliculaoSerieService {
    PeliculaoSerie crear(PeliculaoSerie p);
    PeliculaoSerie getByName(String titulo);
    Set<PeliculaoSerie> getByGenre(Long genre);
    List<PeliculaoSerie> getOrder(String order);
    PeliculaoSerie modificar(PeliculaoSerie p);
    String eliminar(Long id) throws Exception;
    List<PeliculaoSerieDto> listar();
}
