package com.example.challengeBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="movies")
public class PeliculaoSerie implements Comparable<PeliculaoSerie>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imagen;
    @Column
    private String titulo;
    @Column
    private Date fechaCreacion;
    @Column
    private int calificacion;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;



    @ManyToMany
    @JoinTable(
            name = "characters_movies",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    @JsonIgnore
    private Set<Personaje> personajes;


    public PeliculaoSerie() {
    }

    public PeliculaoSerie(String imagen, String titulo, Date fechaCreacion, int calificacion, Genero genero, Set<Personaje> personajes) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.genero = genero;
        this.personajes = personajes;
    }

    public Long getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Set<Personaje> getPersonajes() {

        return personajes;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }

    @Override
    public int compareTo(PeliculaoSerie o) {
        if (getFechaCreacion() == null || o.getFechaCreacion() == null)
            return 0;
        return getFechaCreacion().compareTo(o.getFechaCreacion());
    }

}
