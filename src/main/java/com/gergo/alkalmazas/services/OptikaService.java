package com.gergo.alkalmazas.services;

import com.gergo.alkalmazas.exception.CannotBeNullOrNegativeException;
import com.gergo.alkalmazas.model.Optika;
import com.gergo.alkalmazas.repository.OptikaRepository;
import org.apache.log4j.Logger;

import java.util.List;

public class OptikaService {

    private static final Logger log = Logger.getLogger(OptikaService.class);
    public boolean addOptika(Optika optika){
        if(0>=optika.getPrice()){
            log.info("Negative or null");
            throw new CannotBeNullOrNegativeException();
        }
        OptikaRepository optikaRepository = new OptikaRepository();
        log.info("Optika stored");
        return optikaRepository.addOptika(optika);
    }

    public List<Optika> optikaList(){
        OptikaRepository optikaRepository = new OptikaRepository();
        List<Optika> loger = optikaRepository.optikaList();
        log.info("Optika list size"+ loger.size());
        return loger;
    }
}
