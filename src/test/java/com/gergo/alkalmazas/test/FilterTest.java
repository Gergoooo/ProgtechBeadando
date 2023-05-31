package com.gergo.alkalmazas.test;

import com.gergo.alkalmazas.model.Filter;
import com.gergo.alkalmazas.model.enums.Publisher;
import com.gergo.alkalmazas.repository.FilterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FilterTest {
    @Test
    public void testRead(){
        FilterRepository filterRepository = new FilterRepository();
        Filter filter = filterRepository.readFilter(-1);
        Assertions.assertNull(filter);
    }

    @Test
    public void testCreate(){
        FilterRepository filterRepository = new FilterRepository();
        Filter filter = new Filter();
        filter.setName("C63");
        filter.setType("UV");
        filter.setPrice(12000);
        Boolean result = filterRepository.addFilter(filter);
        Assertions.assertNotNull(filter);
        Assertions.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        FilterRepository filterRepository = new FilterRepository();
        Filter filter = new Filter();
        filter.setName("C63");
        filter.setType("UV");
        filter.setPrice(12000);
        filterRepository.addFilter(filter);
        Filter createdFilter =  filterRepository.readFilter(filter.getId());
        createdFilter.setName("C67");
        filterRepository.updateFilter(createdFilter);
        Filter updatedFilter =  filterRepository.readFilter(filter.getId());
        Assertions.assertEquals(createdFilter.getName(),updatedFilter.getName());
    }

    @Test
    public void testListAllElements(){
        FilterRepository filterRepository = new FilterRepository();
        int originalSize = filterRepository.filterList().size();
        Filter filter = new Filter();
        filter.setName("C63");
        filter.setType("UV");
        filter.setPrice(12000);
        filterRepository.addFilter(filter);
        int updatedSize = filterRepository.filterList().size();
        Assertions.assertEquals(originalSize + 1,updatedSize);
    }
}
