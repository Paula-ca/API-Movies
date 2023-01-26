package com.example.challengeBackEnd.model;

import java.util.Set;

public class GeneroDto {
    private String nombre;
    private String imagen;
    private Set<PeliculaoSerie> peliculas;

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
