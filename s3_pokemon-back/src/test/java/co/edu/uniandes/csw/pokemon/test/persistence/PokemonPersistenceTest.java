/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.test.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class PokemonPersistenceTest {
    
    @Inject
    PokemonPersistence calP;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PokemonEntity> data = new ArrayList<>();
    
    //DESPLIEGA LA APLICACION
    @Deployment
    public static JavaArchive createDeployement() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PokemonEntity.class.getPackage())
                .addPackage(PokemonPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");

    }
    
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    private void clearData() {
        //CAMBIAR SQL
        em.createQuery("delete from PokemonEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PokemonEntity entity = factory.manufacturePojo(PokemonEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }    
    
    
    //TEST DEL CREATE
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PokemonEntity calificacion = factory.manufacturePojo(PokemonEntity.class);
        PokemonEntity result = calP.create(calificacion);
        Assert.assertNotNull(result);
    }    
    
    //TEST DEL UPDATE
    @Test
    public void updateCalificacionTest() {
        PokemonEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);
        newEntity.setId(entity.getId());
        calP.update(newEntity);
        PokemonEntity resp = em.find(PokemonEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    //TEST DEL DELETE
    @Test
    public void deleteTest() {
        PokemonEntity entity = data.get(0);
        calP.delete(entity.getId());
        PokemonEntity deleted = em.find(PokemonEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }   
    
    
    
    
    
    
}
