package com.example.challengeBackEnd.service.impl;

import com.example.challengeBackEnd.model.PeliculaoSerie;
import com.example.challengeBackEnd.model.Personaje;
import com.example.challengeBackEnd.repository.IPeliculaOSerieRepository;
import com.example.challengeBackEnd.repository.IPersonajeRepository;
import com.example.challengeBackEnd.service.IPersonajeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PersonajeService implements IPersonajeService{
    @Autowired
    IPersonajeRepository personajeRepository;
    @Autowired
    IPeliculaOSerieRepository peliculaOSerieRepository;
    @Autowired
    ObjectMapper mapper;
    public static final Logger logger = Logger.getLogger(PeliculaoSerieService.class);


    @Override
    public Personaje crear(Personaje p) {

        if(p.getNombre()!=null &&
        p.getImagen()!= null &&
        p.getHistoria()!=null){
            return personajeRepository.save(p);
        }
        else {
            return null;
        }


    }

    @Override
    public Personaje getByName(String name) {
        List<Personaje> personajes = personajeRepository.findAll();
        Personaje personajeEncontrado = null;
        for(Personaje personaje:personajes){
            if(personaje.getNombre().equals(name)){
                personajeEncontrado =  personaje;
            }
        }
        return personajeEncontrado;
    }

    @Override
    public List<Personaje> getByAge(int age) {
        List<Personaje> personajes = personajeRepository.findAll();
        List<Personaje> personajesEncontrados =new ArrayList<>();
        for(Personaje personaje:personajes){
            if(personaje.getEdad()==age){
                personajesEncontrados.add(personaje);
            }
        }
        return personajesEncontrados;
    }

    @Override
    public List<Personaje> getByMovie(Long idMovie) {
        List<Personaje> personajesEncontrados = new ArrayList<>();
        List<PeliculaoSerie> peliculas = peliculaOSerieRepository.findAll();
        for(PeliculaoSerie pelicula : peliculas){
            if(pelicula.getId()==idMovie){
                Set<Personaje> personajes = pelicula.getPersonajes();
                personajesEncontrados = mapper.convertValue(personajes, List.class);
            }
        }
        return personajesEncontrados;
    }

    @Override
    public Personaje modificar(Personaje p) {
        Personaje personaje  = this.find(p.getId());
        if(p.getNombre() != null) {
            personaje.setNombre(p.getNombre());
        }
        if(p.getEdad()!=0){
            personaje.setEdad(p.getEdad());
        }
        if(p.getImagen()!=null){
            personaje.setImagen(p.getImagen());
        }
        if(p.getHistoria()!=null){
            personaje.setHistoria(p.getHistoria());
        }
        if(p.getPeso()!=personaje.getPeso()){
            personaje.setPeso(p.getPeso());

        }
        return personajeRepository.save(personaje);


    }

    @Override
    public String eliminar(Long id) throws Exception {
        logger.debug("Eliminando Personaje con id " + id);
        Personaje p = this.find(id);
        if(p!= null){
            personajeRepository.delete(p);
            logger.info("Perosnaje con id "+ id+" eliminado");
            return "Personaje eliminado con id "+id;
        }else{
            logger.error("Personaje con id " + id +"");
            throw new Exception("Personaje con id "+id+" no encontrado.");
        }
    }

    @Override
    public List<Personaje> listar() {
        List<Personaje> personajes =  personajeRepository.findAll();
        for(Personaje personaje : personajes){
            personaje.setPeliculas(peliculaOSerieRepository.findPeliculasPorPersonaje(personaje.getId()));
        }
        return personajes;
    }

    public Personaje find(Long id){
        return personajeRepository.findById(id).orElse(null);
    }

}
