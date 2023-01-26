package com.example.challengeBackEnd.service.impl;


import com.example.challengeBackEnd.model.Genero;
import com.example.challengeBackEnd.model.GeneroDto;
import com.example.challengeBackEnd.repository.IGeneroRepository;
import com.example.challengeBackEnd.service.IGeneroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneroService implements IGeneroService {

    @Autowired
    IGeneroRepository generoRepository;

    @Autowired
    ObjectMapper mapper;

    public static final Logger logger = Logger.getLogger(GeneroService.class);

    @Override
    public Genero crear(Genero g) {
        if(g.getNombre()!=null&&
        g.getImagen()!=null ){
            return generoRepository.save(g);
        }else{
            return null;
        }
    }

    @Override
    public Genero modificar(Genero g) {
        Genero genero = generoRepository.findById(g.getId()).orElse(null);
        if(g.getNombre()!=null){
            genero.setNombre(g.getNombre());
        }if(g.getImagen()!=null){
            genero.setImagen(g.getImagen());
        }if(g.getPeliculas()!=null){
            genero.setPeliculas(g.getPeliculas());
        }
        return generoRepository.save(genero);
    }

    @Override
    public String eliminar(Long id) throws Exception {
        Genero g  = generoRepository.findById(id).orElse(null);
        logger.debug("Eliminando genero con id: " + id);
        if(g!=null){
            generoRepository.delete(g);
            logger.info("Genero eliminado con id: "+ id);
            return "Genero id "+id+" eliminado.";

        } else {
            logger.error("Genero con id " + id + " no encontrado");
            throw new Exception("Genero con id " + id + " no encontrado");
        }

    }

    @Override
    public List<GeneroDto> listar() {
        List<Genero> lista = generoRepository.findAll();
        List<GeneroDto> listaDTO = new ArrayList<>();
        for(Genero genero :lista){
            listaDTO.add(mapper.convertValue(genero,GeneroDto.class));
        }
        return listaDTO;
    }
}
