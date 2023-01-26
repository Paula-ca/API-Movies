package com.example.challengeBackEnd.controller;


import com.example.challengeBackEnd.model.Personaje;
import com.example.challengeBackEnd.service.impl.PersonajeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    PersonajeService personajeService;
    public static final Logger logger = Logger.getLogger(PersonajeController.class);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Personaje> crearPersonaje(@RequestBody Personaje personaje){
        ResponseEntity response = null;
        logger.debug("Agregando personaje...");
        if(personaje != null) {

            response = new ResponseEntity(personajeService.crear(personaje),HttpStatus.OK);
            logger.info("Personaje agregado con id "+personaje.getId());

        } else {
            response = new ResponseEntity("Personaje no agregado",HttpStatus.NOT_FOUND);
            logger.error("Error al agregar un personaje.");
        }

        return response;

    }
    @GetMapping("/name")
    public ResponseEntity<Personaje> personajePorNombre(@RequestParam String name){
        ResponseEntity response = null;
        Personaje personajeName = personajeService.getByName(name);
        logger.debug("Buscando personaje por nombre...");
        if(personajeName!=null){
            response = new ResponseEntity(personajeName,HttpStatus.OK);
            logger.info("Personaje encontrado nombre: "+personajeName.getNombre());
        }else{
            response = new ResponseEntity("Personaje con nombre "+name+" no encontrado",HttpStatus.NOT_FOUND);
            logger.error("No se encontro ningún personaje con nombre "+name);
        }
        return response;
    }
    @GetMapping("/age")
    public ResponseEntity<List<Personaje>> personajesPorEdad(@RequestParam int age){
        ResponseEntity response = null;
        List<Personaje> personajesByAge = personajeService.getByAge(age);
        logger.debug("Buscando personaje por edad...");
        if(personajesByAge.size()>0){
            response = new ResponseEntity(personajesByAge,HttpStatus.OK);
            logger.info("Personjes encontrados con "+age+" años");
        }else{
            response = new ResponseEntity("No se encontraron personajes con "+age+" años.",HttpStatus.NOT_FOUND);
            logger.error("No se encontraron personajes con "+age+" años");
        }
        return response;
    }
    @GetMapping("/movieid")
    public ResponseEntity<List<Personaje>> personajesPorPelicula(@RequestParam Long movieid){
        ResponseEntity response = null;
        List<Personaje> personajeByMovie = personajeService.getByMovie(movieid);
        logger.debug("Buscando personajes por pelicula...");
        if(personajeByMovie.size()>0){
            response = new ResponseEntity(personajeByMovie,HttpStatus.OK);
            logger.info("Personajes encontrados de pelicula "+movieid);
        }else{
            response = new ResponseEntity("La pelicula con id "+movieid+" no se encuentra.",HttpStatus.NOT_FOUND);
            logger.error("No se encontraron personajes de la pelicula "+movieid);
        }
        return response;
    }
    @GetMapping()
    public ResponseEntity<List<Personaje>> listarPersonajes() {
        ResponseEntity response = null;
        List<Personaje> listPersonajes = personajeService.listar();
        logger.debug("Listando personajes...");
        if(listPersonajes.size()> 0){
            response = new ResponseEntity(listPersonajes, HttpStatus.OK);
            logger.info("Personajes listados.");
        } else {
            response = new ResponseEntity("No se encontraron personajes", HttpStatus.NOT_FOUND);
            logger.error("No se encontraron personajes");
        }

        return response;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Personaje> update(@RequestBody Personaje personaje){
        ResponseEntity response = null;
        logger.debug("Modificando personaje...");

        if(personaje != null){
            response = new ResponseEntity(personajeService.modificar(personaje), HttpStatus.OK);
            logger.info("Personaje modificado con id "+personaje.getId());
        } else {
            response = new ResponseEntity("No se pudo actualizar el personaje", HttpStatus.NOT_FOUND);
            logger.error("Error al modificar el personaje.");
        }

        return response;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(personajeService.eliminar(id));
    }

}
