package com.gergo.alkalmazas.repository;

import com.gergo.alkalmazas.model.Kamera;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class KameraRepository {

    private static final Logger log = Logger.getLogger(KameraHazRepository.class);

    //Kamera Haz id,publisher,name,price
    //Optika id,publisher, compability, price
    //filter id , publisher, type, price
    //Kamera haz_id, optika_id filter_id

    //insert into Haz(id) values(1)
    public void Create(Kamera kamera){}

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
}
