package com.gergo.alkalmazas.test;

import com.gergo.alkalmazas.model.Filter;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

public class DbTest {
    private static final Logger log = Logger.getLogger(DbTest.class);

    @Test
    public void dBTestConnection(){
        log.info("Connection Succesfull");
        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("PersistenceDB");
            int lastID = createFilters(emf);
            updateFilter(emf,lastID);
            deletFilter(emf,lastID);
            readAllFilters(emf);
        }
        catch (Exception e)
        {
            log.error("DB connection error. Reason: " +e.getMessage(),e);
        }
    }

    //Create
    private int createFilters(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        int lastID = -1;
        em.getTransaction().begin();
        for(int i = 0;10>i;i++){
            Filter filter = new Filter();
            filter.setName("Nikon valami"+i);
            filter.setType("UV"+i);
            filter.setPrice(i*10000);
            em.persist(filter);
            lastID = filter.getId();
        }
        em.getTransaction().commit();
        log.info("Created");
        return lastID;
    }
    //Update/Read
    private void updateFilter(EntityManagerFactory emf,int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Filter f = em.find(Filter.class,id);
        f.setPrice(0);
        em.merge(f);
        em.getTransaction().commit();
        log.info("Updated");
    }

    //Delete
    private void deletFilter(EntityManagerFactory emf,int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Filter f = em.find(Filter.class,id);
        em.remove(f);
        em.getTransaction().commit();
        log.info("Deleted");
    }
    //ReadAllLines
    private void readAllFilters(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Filter> query = em.createQuery("Select f from Filter f", Filter.class);
        for (Filter f:query.getResultList()
             ) {
            log.info(f);
        }
        em.getTransaction().commit();
    }

}
