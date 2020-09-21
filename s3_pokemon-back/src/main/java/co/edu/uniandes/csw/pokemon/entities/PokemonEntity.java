/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.entities;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class PokemonEntity extends BaseEntity implements Serializable {

    private String nombre;
    private String descripcion;
    private double peso;
    private double altura;
    //Ya esta la enumeracion creada
    private PokemonType tipo;
    private String debilidad;
    private long idPokemon;
   
    //Un Pokemon tiene por lo menos un ataque, el cual consta de un nombre y de un daño el cuál es un valor numérico de 10 a 100
    
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public PokemonType getTipo() {
        return tipo;
    }

    public void setTipo(PokemonType tipo) {
        this.tipo = tipo;
    }

    public String getDebilidad() {
        return debilidad;
    }

    public void setDebilidad(String debilidad) {
        this.debilidad = debilidad;
    }

    public long getIdPokemon() {
        return idPokemon;
    }

    public void setId(long id) {
        this.idPokemon = id;
    }


    
    
}
