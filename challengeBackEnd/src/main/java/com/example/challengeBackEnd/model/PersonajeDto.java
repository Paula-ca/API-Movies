package com.example.challengeBackEnd.model;

public class PersonajeDto {
    private String imagen;
    private String nombre;
    private PeliculaoSerie peliculas;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PeliculaoSerie getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(PeliculaoSerie peliculas) {
        this.peliculas = peliculas;
    }
}
