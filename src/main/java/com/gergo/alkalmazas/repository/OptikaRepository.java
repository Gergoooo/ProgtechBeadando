package com.gergo.alkalmazas.repository;

import com.gergo.alkalmazas.model.Optika;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class OptikaRepository {
    private static final Logger log = Logger.getLogger(FilterRepository.class);


    //UI implementaciohoz o kell!
    //Add
    public boolean addOptika(Optika optika){
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(optika);
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
    public boolean updateOptika(Optika optika){
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(optika);
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
    public Optika readOptika(Integer id){
        try {
            Optika filter;
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            filter =  em.find(Optika.class,id);
            em.getTransaction().commit();
            return filter;
        }
        catch (Exception e)
        {
            log.error("DB entityCreateException" +e.getMessage(),e);
            return null;
        }
    }

    //List all elements
    public List<Optika> optikaList() {
        try {
            List<Optika> optikaList;
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Optika> query = em.createQuery("Select f from Optika f", Optika.class);
            optikaList = query.getResultList();
            em.getTransaction().commit();
            return optikaList;
        }
        catch (Exception e)
        {
            log.error("DB entityCreateException" +e.getMessage(),e);
            return null;
        }
    }
}
