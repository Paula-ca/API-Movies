package com.example.challengeBackEnd.controller;

import com.example.challengeBackEnd.model.PeliculaoSerie;
import com.example.challengeBackEnd.model.PeliculaoSerieDto;
import com.example.challengeBackEnd.service.impl.PeliculaoSerieService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movies")
public class PeliculaoSerieController {
    @Autowired
    PeliculaoSerieService peliculaoSerieService;

    public static final Logger logger = Logger.getLogger(PeliculaoSerieController.class);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<PeliculaoSerie> crearPelicula(@RequestBody PeliculaoSerie pelicula){
        ResponseEntity response = null;
        logger.debug("Agregando pelicula o serie...");
        if(pelicula != null) {

            response = new ResponseEntity(peliculaoSerieService.crear(pelicula), HttpStatus.OK);
            logger.info("Pelicula o serie con id "+pelicula.getId()+" agregada");
        } else {
            response = new ResponseEntity("Pelicula no agregada",HttpStatus.NOT_FOUND);
            logger.error("Error al agregar pelicula");
        }

        return response;
    }

    @GetMapping("/name")
    public ResponseEntity<PeliculaoSerie> peliculaPorNombre(@RequestParam(required = true) String name){
        ResponseEntity response = null;
        logger.debug("Buscando pelicula por nombre...");
        PeliculaoSerie peliEncontrada = peliculaoSerieService.getByName(name);
        if(peliEncontrada!=null){
            response = new ResponseEntity(peliEncontrada,HttpStatus.OK);
            logger.info("Pelicula encontrada "+peliEncontrada.getTitulo());
        }else{
            response = new ResponseEntity("Pelicula con nombre "+name+" no encontrado",HttpStatus.NOT_FOUND);
            logger.error("Pelicula con nombre "+name+" no encontrada");
        }
        return response;
    }
    @GetMapping("/genre")
    public ResponseEntity<List<PeliculaoSerieDto>> peliculaPorGenero(@RequestParam(required = true) Long  genre_id){
        ResponseEntity response = null;
        Set<PeliculaoSerie> peliculasPorGenero = peliculaoSerieService.getByGenre(genre_id);
        logger.debug("Buscando pelicula por genero...");
        if(peliculasPorGenero!=null){
            response = new ResponseEntity(peliculasPorGenero,HttpStatus.OK);
            logger.info("Peliculas encontradas del genero "+genre_id);
        }else{
            response = new ResponseEntity("Pelicula con genero "+genre_id+" no encontrado",HttpStatus.NOT_FOUND);
            logger.error("No se enconraron peliculas del genero con id "+genre_id);
        }
        return response;
    }
    @GetMapping("/order")
    public ResponseEntity<List<PeliculaoSerie>> orderPeliculas(@RequestParam String order){
        ResponseEntity response = null;
        List<PeliculaoSerie> orderPelis = peliculaoSerieService.getOrder(order);
        logger.debug("Ordenando peliculas...");
        if(orderPelis!=null){
            response = new ResponseEntity(orderPelis,HttpStatus.OK);
            logger.info("Peliculas ordenadas");
        }else{
            response = new ResponseEntity("Las peliculas no se encuentran ordenadas o no existen",HttpStatus.NOT_FOUND);
            logger.error("Error al ordenar las peliculas.");
        }
        return response;
    }

    @GetMapping
        public ResponseEntity<List<PeliculaoSerie>> listarPeliculas() {
        ResponseEntity response = null;
        logger.debug("Listando peliculas...");
        List<PeliculaoSerieDto> listPeliculas = peliculaoSerieService.listar();
        if(listPeliculas.size()> 0){
            response = new ResponseEntity(listPeliculas, HttpStatus.OK);
            logger.info("Peliculas listadas.");
        } else {
            response = new ResponseEntity("No se encontraron peliculas o series", HttpStatus.NOT_FOUND);
            logger.error("Error al listar las peliculas");
        }
        return response;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<PeliculaoSerie> update(@RequestBody PeliculaoSerie peliculaoSerie){
        ResponseEntity response = null;
    logger.debug("Modificando pelicula...");

        if(peliculaoSerie != null){
            response = new ResponseEntity(peliculaoSerieService.modificar(peliculaoSerie), HttpStatus.OK);
            logger.info("Peliculas modificada con id "+peliculaoSerie.getId());
        } else {
            response = new ResponseEntity("No se pudo actualizar la pelicula o serie.", HttpStatus.NOT_FOUND);
            logger.error("Error al modificar pelicula");
        }

        return response;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(peliculaoSerieService.eliminar(id));
    }
}
