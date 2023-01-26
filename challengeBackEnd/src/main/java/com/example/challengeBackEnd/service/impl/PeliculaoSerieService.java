package com.example.challengeBackEnd.service.impl;

import com.example.challengeBackEnd.model.PeliculaoSerie;
import com.example.challengeBackEnd.model.PeliculaoSerieDto;
import com.example.challengeBackEnd.repository.IGeneroRepository;
import com.example.challengeBackEnd.repository.IPeliculaOSerieRepository;
import com.example.challengeBackEnd.service.IPeliculaoSerieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeliculaoSerieService implements IPeliculaoSerieService{

    @Autowired
    IPeliculaOSerieRepository peliculaOSerieRepository;
    IGeneroRepository generoRepository;
    @Autowired
    ObjectMapper mapper;

    public static final Logger logger = Logger.getLogger(PeliculaoSerieService.class);

    @Override
    public PeliculaoSerie crear(PeliculaoSerie p) {
        if (p.getCalificacion() != 0 &&
                p.getFechaCreacion() != null &&
                p.getImagen() != null &&
                p.getTitulo() != null &&
                p.getGenero()!=null &&
                p.getPersonajes().size() > 0) {
            return peliculaOSerieRepository.save(p);
        }else{
            return null;
        }
    }

    @Override
    public PeliculaoSerie getByName(String titulo) {

        List<PeliculaoSerie> pelis = peliculaOSerieRepository.findAll();
        PeliculaoSerie peli = null;
        for (PeliculaoSerie pelicula : pelis) {
            if (pelicula.getTitulo().equals(titulo)) {
                peli = pelicula;
            }
        }
        return peli;
    }

    @Override
    public Set<PeliculaoSerie> getByGenre(Long id) {
        return new HashSet<>(peliculaOSerieRepository.findByGeneroId(id));
    }

    @Override
    public List<PeliculaoSerie> getOrder(String order) {

        List<PeliculaoSerie> pelis = peliculaOSerieRepository.findAll();
        Collections.sort(pelis);
        if(order.equals("ASC")) {
            Collections.sort(pelis);
        }if(order.equals("DESC")) {
            Collections.reverse(pelis);
        }

        return pelis;

    }

    @Override
    public PeliculaoSerie modificar(PeliculaoSerie p) {
        PeliculaoSerie peli = this.find(p.getId());
        if (p.getTitulo() != null) {
            peli.setTitulo(p.getTitulo());
        }
        if (p.getCalificacion() != 0) {
            peli.setCalificacion(p.getCalificacion());
        }
        if (p.getImagen() != null) {
            peli.setImagen(p.getImagen());
        }
        if (p.getFechaCreacion() != null) {
            peli.setFechaCreacion(p.getFechaCreacion());
        }if(p.getPersonajes().size()>0) {
            peli.setPersonajes(p.getPersonajes());
        }
            return peliculaOSerieRepository.save(peli);

    }

    @Override
    public String eliminar(Long id) throws Exception {
        PeliculaoSerie p = this.find(id);
        logger.debug("Eliminando pelicula con id: "+id);
        if(p!= null){
            peliculaOSerieRepository.delete(p);
            logger.info("Pelicula con id "+id+" eliminada.");
            return "Pelicula con id "+id+" eliminada.";
        }else{
            logger.error("Pelicula con id "+id+" no encontrada.");
            throw new Exception("Pelicula con id "+id+" no encontrada.");
        }
    }

    @Override
    public List<PeliculaoSerieDto> listar() {
        List<PeliculaoSerie> lista = peliculaOSerieRepository.findAll();
        List<PeliculaoSerieDto> listaDTO = new ArrayList<>();
        for(PeliculaoSerie pelicula :lista){
            listaDTO.add(mapper.convertValue(pelicula,PeliculaoSerieDto.class));
        }
        return listaDTO;
    }

    public PeliculaoSerie find(Long id) {
        return peliculaOSerieRepository.findById(id).orElse(null);
    }




}