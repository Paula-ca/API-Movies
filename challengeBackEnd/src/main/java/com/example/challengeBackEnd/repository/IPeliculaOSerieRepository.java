package com.example.challengeBackEnd.repository;

import com.example.challengeBackEnd.model.PeliculaoSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IPeliculaOSerieRepository extends JpaRepository<PeliculaoSerie,Long> {
    List<PeliculaoSerie> findByGeneroId(Long genero);
    @Query(value = "SELECT * FROM db_movies.movies AS m inner JOIN characters_movies AS cm on cm.pelicula_id = m.id WHERE cm.personaje_id = ?1",
            nativeQuery = true)
    Set<PeliculaoSerie> findPeliculasPorPersonaje(long personaje_id);
}
