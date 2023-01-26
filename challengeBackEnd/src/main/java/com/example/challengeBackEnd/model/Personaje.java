package com.example.challengeBackEnd.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="characters")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imagen;
    @Column
    private String nombre;
    @Column
    private int edad;
    @Column
    private double peso;
    @Column
    private String historia;

    @ManyToMany(mappedBy = "personajes")
    private Set<PeliculaoSerie> peliculas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personaje() {
    }

    public Personaje(Long id, String imagen, String nombre, int edad, double peso, String historia, Set<PeliculaoSerie> peliculas) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.peliculas = peliculas;
    }

    public Personaje(String imagen, String nombre, int edad, double peso, String historia, Set<PeliculaoSerie> peliculas) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.peliculas = peliculas;
    }

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Set<PeliculaoSerie> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<PeliculaoSerie> peliculas) {
        this.peliculas = peliculas;
    }
}
