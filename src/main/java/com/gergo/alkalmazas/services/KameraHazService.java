package com.gergo.alkalmazas.services;

import com.gergo.alkalmazas.exception.CannotBeNullOrNegativeException;
import com.gergo.alkalmazas.model.KameraHaz;
import com.gergo.alkalmazas.repository.KameraHazRepository;
import com.gergo.alkalmazas.repository.KameraRepository;
import org.apache.log4j.Logger;

import java.util.List;

public class KameraHazService {

    private static final Logger log = Logger.getLogger(KameraHazService.class);
    public boolean addKameraHaz(KameraHaz kameraHaz){
        if(0>=kameraHaz.getPrice()){
            log.info("Negative or null");
            throw new CannotBeNullOrNegativeException();
        }
        KameraHazRepository kameraHazRepository = new KameraHazRepository();
        log.info("Kamera haz soterd");
        return kameraHazRepository.addKameraHaz(kameraHaz);
    }

    public List<KameraHaz> kameraHazList(){
        KameraHazRepository kameraHazRepository = new KameraHazRepository();
        List<KameraHaz> loger = kameraHazRepository.KamerahazList();
        log.info("Kamera haz list size:"+loger.size());
        return loger;
    }
    
}
