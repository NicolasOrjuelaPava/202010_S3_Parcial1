/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class PokemonPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PokemonPersistence.class.getName());
    
    //nombre de la base de datos creada
    @PersistenceContext(unitName="pokemon")
    protected EntityManager em;
 
    //CREACION
    public PokemonEntity create(PokemonEntity pokemon) {
        LOGGER.log(Level.INFO, "Se esta creando un nuevo elemento");
        em.persist(pokemon);
        LOGGER.log(Level.INFO, "Se ha creado el elemento");
        return pokemon;
    }
    
    
    //OBTENER TODOS
     public List<PokemonEntity> findAll() {
        LOGGER.log(Level.INFO, "Buscando todos los elementos");
        //CAMBIAR EL SQL
        TypedQuery query = em.createQuery("select u from PokemonEntity u", PokemonEntity.class);
        return query.getResultList();
    }   
    
    
     //OBTENER POR NOMBRE
    public PokemonEntity find(Long calId) {
        LOGGER.log(Level.INFO, "Buscando el elemento con id = {0}", calId);
        return em.find(PokemonEntity.class, calId);
    }
    

    //ACTUALIZAR
    public PokemonEntity update(PokemonEntity pokemonEntity) {
        LOGGER.log(Level.INFO, "Actualizando el elemento con id={0}", pokemonEntity.getId());
        return em.merge(pokemonEntity);
    }
    
    //BORRAR
    public void delete(Long calId) {
        LOGGER.log(Level.INFO, "Borrando el elemento con id={0}", calId);
        PokemonEntity calificacion = em.find(PokemonEntity.class, calId);
        em.remove(calificacion);
    }
    
    
    
}
