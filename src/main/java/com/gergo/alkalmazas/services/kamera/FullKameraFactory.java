package com.gergo.alkalmazas.services.kamera;

import com.gergo.alkalmazas.model.Filter;
import com.gergo.alkalmazas.model.Kamera;
import com.gergo.alkalmazas.model.KameraHaz;
import com.gergo.alkalmazas.model.Optika;
import com.gergo.alkalmazas.model.enums.Publisher;
import com.gergo.alkalmazas.repository.FilterRepository;
import com.gergo.alkalmazas.repository.KameraHazRepository;
import com.gergo.alkalmazas.repository.OptikaRepository;

import java.util.ArrayList;
import java.util.List;

public class FullKameraFactory implements IKameraFactory{
    @Override
    public List<Kamera> getSuggestions(Publisher publisher) {
        KameraHazRepository kameraHazRepository = new KameraHazRepository();
        OptikaRepository optikaRepository = new OptikaRepository();
        FilterRepository filterRepository = new FilterRepository();

        List<KameraHaz> kameraHazList = kameraHazRepository.KamerahazList();
        List<Optika> optikaList = optikaRepository.optikaList();
        List<Filter> filterList = filterRepository.filterList();

        List<Kamera> resultList = new ArrayList<>();
        for (KameraHaz haz:kameraHazList
        ) {
            if (haz.getPublisher().equals(publisher))
            {
                for (Optika optika : optikaList
                ) {
                    if (optika.getPublisher().equals(publisher) || optika.getCompability().equals(publisher)) {
                        for (Filter filter: filterList
                             ) {
                            Kamera kamera = new Kamera();
                            kamera.setHazId(haz.getId());
                            kamera.setOptikaId(optika.getId());
                            kamera.setFilterId(filter.getId());
                            resultList.add(kamera);
                        }
                    }
                }
            }
        }
        return resultList;
    }

}
