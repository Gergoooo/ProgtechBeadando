package com.gergo.alkalmazas.test;

import com.gergo.alkalmazas.model.Optika;
import com.gergo.alkalmazas.model.enums.Publisher;
import com.gergo.alkalmazas.repository.OptikaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OptikaTest {
    @Test
    public void testRead(){
        OptikaRepository optikaRepository = new OptikaRepository();
        Optika optika = optikaRepository.readOptika(-1);
        Assertions.assertNull(optika);
    }

    @Test
    public void testCreate(){
        OptikaRepository optikaRepository = new OptikaRepository();
        Optika optika = new Optika();
        optika.setName("Fujitsu 8X");
        optika.setPublisher("Fujitsu");
        optika.setCompability(Publisher.nikon);
        optika.setPrice(450000);
        Boolean result = optikaRepository.addOptika(optika);
        Assertions.assertNotNull(optika);
        Assertions.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        OptikaRepository optikaRepository = new OptikaRepository();
        Optika optika = new Optika();
        optika.setName("Fujitsu 8X");
        optika.setPublisher("Fujitsu");
        optika.setCompability(Publisher.nikon);
        optika.setPrice(450000);
        optikaRepository.addOptika(optika);
        Optika createdOptika =  optikaRepository.readOptika(optika.getId());
        createdOptika.setName("Fujitsu 6X");
        optikaRepository.updateOptika(createdOptika);
        Optika updatedOptika =  optikaRepository.readOptika(optika.getId());
        Assertions.assertEquals(createdOptika.getName(),updatedOptika.getName());
    }

    @Test
    public void testListAllElements(){
        OptikaRepository optikaRepository = new OptikaRepository();
        int originalSize = optikaRepository.optikaList().size();
        Optika optika = new Optika();
        optika.setName("Fujitsu 8X");
        optika.setPublisher("Fujitsu");
        optika.setCompability(Publisher.nikon);
        optika.setPrice(450000);
        optikaRepository.addOptika(optika);
        int updatedSize = optikaRepository.optikaList().size();
        Assertions.assertEquals(originalSize + 1,updatedSize);
    }
}
