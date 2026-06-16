package com.example.demo.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EchantillonTest {

    @Test
    void testConstructeurVideEtSetters() {
        Echantillon e = new Echantillon();
        e.setNombreDeVoitures(10);
        e.setPrixMoyen(15000);
        
        assertEquals(10, e.getNombreDeVoitures());
        assertEquals(15000, e.getPrixMoyen());
    }

    @Test
    void testConstructeurAvecParametres() {
        Echantillon e = new Echantillon(5, 20000);
        assertEquals(5, e.getNombreDeVoitures());
        assertEquals(20000, e.getPrixMoyen());
    }
}
