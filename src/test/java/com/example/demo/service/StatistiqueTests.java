package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StatistiqueTests {

    @Autowired
    private Statistique statistique;

    @Test
    void testPrixMoyenAvecUneVoiture() {
        Voiture v = new Voiture("Toyota", 20000);
        statistique.ajouter(v);
        
        Echantillon echantillon = statistique.prixMoyen();
        
        assertEquals(1, echantillon.getNombreDeVoitures());
        assertEquals(20000, echantillon.getPrixMoyen());
    }

    @Test
    void testPrixMoyenAvecPlusieursVoitures() {
        statistique.ajouter(new Voiture("Peugeot", 10000));
        statistique.ajouter(new Voiture("Audi", 30000));
        
        Echantillon echantillon = statistique.prixMoyen();
        
        assertEquals(2, echantillon.getNombreDeVoitures());
        assertEquals(20000, echantillon.getPrixMoyen()); // (10000 + 30000) / 2
    }

    @Test
    void testPrixMoyenSansVoitureDevraitLancerException() {
        // Dans StatistiqueImpl, la division par zéro (nombreDeVoitures = 0) 
        // lance une ArithmeticException
        assertThrows(ArithmeticException.class, () -> {
            statistique.prixMoyen();
        });
    }
}
