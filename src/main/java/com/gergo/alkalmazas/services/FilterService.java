package com.gergo.alkalmazas.services;

import com.gergo.alkalmazas.exception.CannotBeNullOrNegativeException;
import com.gergo.alkalmazas.model.Filter;
import com.gergo.alkalmazas.repository.FilterRepository;
import org.apache.log4j.Logger;

import java.util.List;

public class FilterService {

    private static final Logger log = Logger.getLogger(FilterService.class);
    public boolean addFilter(Filter filter){
        if(0>=filter.getPrice()){
            log.info("Negative or null");
            throw new CannotBeNullOrNegativeException();
        }
        FilterRepository filterRepository = new FilterRepository();
        log.info("Filter stored");
        return filterRepository.addFilter(filter);
    }

    public List<Filter> filterList(){
        FilterRepository filterRepository = new FilterRepository();
        List<Filter> loger = filterRepository.filterList();
        log.info("Filter list size: "+loger.size());
        return loger;
    }
}
