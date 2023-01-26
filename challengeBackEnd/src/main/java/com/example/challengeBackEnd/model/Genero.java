package com.example.challengeBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="genres")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String imagen;

    @OneToMany(mappedBy = "genero")
    @JsonIgnore
    private Set<PeliculaoSerie> peliculas;

    public Genero() {
    }

    public Genero(String nombre, String imagen, Set<PeliculaoSerie> peliculas) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.peliculas = peliculas;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Set<PeliculaoSerie> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<PeliculaoSerie> peliculas) {
        this.peliculas = peliculas;
    }
}
