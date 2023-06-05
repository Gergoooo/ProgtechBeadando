package com.gergo.alkalmazas.repository;
import com.gergo.alkalmazas.model.Kamera;
import com.gergo.alkalmazas.model.KameraHaz;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class KameraHazRepository {
    private static final Logger log = Logger.getLogger(KameraHazRepository.class);


    //UI implementaciohoz o kell!
    //Add
    public boolean addKameraHaz(KameraHaz kameraHaz){
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(kameraHaz);
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
    public boolean updateKameraHaz(KameraHaz kameraHaz){
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(kameraHaz);
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
    static public KameraHaz readKamerahaz(Integer id){
        try {
            KameraHaz kameraHaz;
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            kameraHaz =  em.find(KameraHaz.class,id);
            em.getTransaction().commit();
            return kameraHaz;
        }
        catch (Exception e)
        {
            log.error("DB entityCreateException" +e.getMessage(),e);
            return null;
        }
    }

    //List all elements
    public static List<KameraHaz> KamerahazList() {
        try {
            List<KameraHaz> kamerahazList;
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<KameraHaz> query = em.createQuery("Select f from KameraHaz f", KameraHaz.class);
            kamerahazList = query.getResultList();
            em.getTransaction().commit();
            return kamerahazList;
        }
        catch (Exception e)
        {
            log.error("DB entityCreateException" +e.getMessage(),e);
            return null;
        }
    }
}
