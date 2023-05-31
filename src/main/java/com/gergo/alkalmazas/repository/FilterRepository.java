package com.gergo.alkalmazas.repository;

import com.gergo.alkalmazas.model.Filter;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class FilterRepository {
    private static final Logger log = Logger.getLogger(FilterRepository.class);


    //UI implementaciohoz o kell!
    //Add
    public boolean addFilter(Filter filter){
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(filter);
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
    public boolean updateFilter(Filter filter){
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(filter);
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
    public Filter readFilter(Integer id){
        try {
            Filter filter;
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            filter =  em.find(Filter.class,id);
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
    public List<Filter> filterList() {
        try {
            List<Filter> filterList;
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Filter> query = em.createQuery("Select f from Filter f", Filter.class);
            filterList = query.getResultList();
            em.getTransaction().commit();
            return filterList;
        }
        catch (Exception e)
        {
            log.error("DB entityCreateException" +e.getMessage(),e);
            return null;
        }
    }

}


