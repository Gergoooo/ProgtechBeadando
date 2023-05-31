package com.gergo.alkalmazas.services;

import com.gergo.alkalmazas.model.Kamera;
import com.gergo.alkalmazas.model.enums.Publisher;
import com.gergo.alkalmazas.repository.KameraRepository;
import com.gergo.alkalmazas.services.kamera.IKameraFactory;
import org.apache.log4j.Logger;

import java.util.List;

public class KeszKameraService {

    private static final Logger log = Logger.getLogger(KeszKameraService.class);
    public List<Kamera> getSuggestion(Boolean isFull, Publisher publisher){
        List<Kamera> result = IKameraFactory.findFactory(isFull).getSuggestions(publisher);
        log.info("Suggestion results count:"+result.size());
        return result;
    }

    public boolean store(Kamera kamera){
        KameraRepository kameraRepository = new KameraRepository();
        log.info("Kamera stored");
        return kameraRepository.addKamera(kamera);
    }
}
