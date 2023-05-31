package com.gergo.alkalmazas.test;

import com.gergo.alkalmazas.model.KameraHaz;
import com.gergo.alkalmazas.model.enums.Publisher;
import com.gergo.alkalmazas.repository.KameraHazRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KameraHazTest {

    @Test
    public void testRead(){
        KameraHazRepository kameraHazRepository = new KameraHazRepository();
        KameraHaz kameraHaz = kameraHazRepository.readKamerahaz(-1);
        Assertions.assertNull(kameraHaz);
    }

    @Test
    public void testCreate(){
        KameraHazRepository kameraHazRepository = new KameraHazRepository();
        KameraHaz kameraHaz = new KameraHaz();
        kameraHaz.setName("Nikon C63");
        kameraHaz.setPublisher(Publisher.nikon);
        kameraHaz.setPrice(450000);
        Boolean result = kameraHazRepository.addKameraHaz(kameraHaz);
        Assertions.assertNotNull(kameraHaz);
        Assertions.assertTrue(result);
    }

    @Test
    public void testUpdate(){
        KameraHazRepository kameraHazRepository = new KameraHazRepository();
        KameraHaz kameraHaz = new KameraHaz();
        kameraHaz.setName("Nikon C63");
        kameraHaz.setPublisher(Publisher.nikon);
        kameraHaz.setPrice(450000);
        kameraHazRepository.addKameraHaz(kameraHaz);
        KameraHaz createdKameraHaz =  kameraHazRepository.readKamerahaz(kameraHaz.getId());
        createdKameraHaz.setName("Nikon C67");
        kameraHazRepository.updateKameraHaz(createdKameraHaz);
        KameraHaz updatedKamerahaz =  kameraHazRepository.readKamerahaz(kameraHaz.getId());
        Assertions.assertEquals(createdKameraHaz.getName(),updatedKamerahaz.getName());
    }

    @Test
    public void testListAllElements(){
        KameraHazRepository kameraHazRepository = new KameraHazRepository();
        int originalSize = kameraHazRepository.KamerahazList().size();
        KameraHaz kameraHaz = new KameraHaz();
        kameraHaz.setName("Nikon C63");
        kameraHaz.setPublisher(Publisher.nikon);
        kameraHaz.setPrice(450000);
        kameraHazRepository.addKameraHaz(kameraHaz);
        int updatedSize = kameraHazRepository.KamerahazList().size();
        Assertions.assertEquals(originalSize + 1,updatedSize);
    }
}
