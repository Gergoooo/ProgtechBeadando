package com.gergo.alkalmazas.repository;

import com.gergo.alkalmazas.model.Kamera;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class KeszKameraRepository {
    private static final Logger log = Logger.getLogger(FilterRepository.class);


    //UI implementaciohoz o kell!
    //Add
    public boolean addKamera(Kamera kamera){
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(kamera);
            em.getTransaction().commit();
            return true;

        }
        catch (Exception e)
        {
            log.error("DB entityCreateException" +e.getMessage(),e);
            return false;
        }
    }

    //Update
    public boolean updateKamera(Kamera kamera){
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(kamera);
            em.getTransaction().commit();
            return true;

        }
        catch (Exception e)
        {
            log.error("DB entityCreateException" +e.getMessage(),e);
            return false;
        }
    }

    //Read
    public Kamera readKamera(Integer id){
        try {
            Kamera kamera;
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            kamera =  em.find(Kamera.class,id);
            em.getTransaction().commit();
            return kamera;
        }
        catch (Exception e)
        {
            log.error("DB entityCreateException" +e.getMessage(),e);
            return null;
        }
    }

    //List all elements
    public List<Kamera> kameraList() {
        try {
            List<Kamera> kameraList;
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Kamera> query = em.createQuery("Select f from Kamera f", Kamera.class);
            kameraList = query.getResultList();
            em.getTransaction().commit();
            return kameraList;
        }
        catch (Exception e)
        {
            log.error("DB entityCreateException" +e.getMessage(),e);
            return null;
        }
    }
}
