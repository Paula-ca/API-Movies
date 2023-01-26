package com.example.challengeBackEnd.controller;

import com.example.challengeBackEnd.model.Genero;
import com.example.challengeBackEnd.model.GeneroDto;
import com.example.challengeBackEnd.model.Personaje;
import com.example.challengeBackEnd.service.impl.GeneroService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GeneroController {
    @Autowired
    GeneroService generoService;

    public static final Logger logger = Logger.getLogger(GeneroController.class);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Personaje> crearGenero(@RequestBody Genero genero){
        ResponseEntity response = null;
        logger.debug("Agregando genero...");
        if(genero != null) {

            response = new ResponseEntity(generoService.crear(genero), HttpStatus.OK);
            logger.info("Genero agregado id "+ genero.getId());

        } else {
            response = new ResponseEntity("Genero no agregado",HttpStatus.NOT_FOUND);
            logger.error("Genero no agregado");
        }

        return response;

    }
    @GetMapping()
    public ResponseEntity<List<GeneroDto>> listarGeneros() {
        ResponseEntity response = null;
        List<GeneroDto> listGeneros = generoService.listar();
        logger.info("listando generos...");
        if(listGeneros.size()> 0){
            response = new ResponseEntity(listGeneros, HttpStatus.OK);
            logger.info("Generos listados.");
        } else {
            response = new ResponseEntity("No se encontraron generos", HttpStatus.NOT_FOUND);
            logger.error("Error al listar generos");
        }

        return response;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Personaje> update(@RequestBody Genero genero){
        ResponseEntity response = null;

logger.debug("Actualizando genero...");
        if(genero != null){
            response = new ResponseEntity(generoService.modificar(genero), HttpStatus.OK);
logger.info("Genero actualizado con id "+genero.getId());
        } else {
            response = new ResponseEntity("No se pudo actualizar el genero", HttpStatus.NOT_FOUND);
logger.error("Error al actualizar genero");
        }

        return response;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(generoService.eliminar(id));
    }

}
