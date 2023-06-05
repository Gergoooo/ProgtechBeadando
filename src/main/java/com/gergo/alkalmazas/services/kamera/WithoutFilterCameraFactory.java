package com.gergo.alkalmazas.services.kamera;

import com.gergo.alkalmazas.model.Kamera;
import com.gergo.alkalmazas.model.KameraHaz;
import com.gergo.alkalmazas.model.Optika;
import com.gergo.alkalmazas.model.enums.Publisher;
import com.gergo.alkalmazas.repository.KameraHazRepository;
import com.gergo.alkalmazas.repository.OptikaRepository;

import java.util.ArrayList;
import java.util.List;

public class WithoutFilterCameraFactory implements IKameraFactory{
    @Override
    public List<Kamera> getSuggestions(Publisher publisher) {
        KameraHazRepository kameraHazRepository = new KameraHazRepository();
        OptikaRepository optikaRepository = new OptikaRepository();

        List<KameraHaz> kameraHazList = kameraHazRepository.KamerahazList();
        List<Optika> optikaList = optikaRepository.optikaList();

        List<Kamera> resultList = new ArrayList<>();
        for (KameraHaz haz:kameraHazList
             ) {
            if (haz.getPublisher().equals(publisher))
            {
                for (Optika optika : optikaList
                ) {
                    if (optika.getPublisher().equals(publisher) || optika.getCompatibility().equals(publisher)) {
                        Kamera kamera = new Kamera();
                        kamera.setHazId(haz.getId());
                        kamera.setOptikaId(optika.getId());
                        resultList.add(kamera);
                    }
                }
            }
        }
        return resultList;
    }


}
