package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoitureTest {

    @Test
    void testConstructeurEtGetters() {
        Voiture v = new Voiture("Tesla", 50000);
        assertEquals("Tesla", v.getMarque());
        assertEquals(50000, v.getPrix());
    }

    @Test
    void testSetters() {
        Voiture v = new Voiture();
        v.setMarque("Renault");
        v.setPrix(20000);
        v.setId(1);
        
        assertEquals("Renault", v.getMarque());
        assertEquals(20000, v.getPrix());
        assertEquals(1, v.getId());
    }

    @Test
    void testToString() {
        Voiture v = new Voiture("BMW", 30000);
        v.setId(42);
        String expected = "Car{marque='BMW', prix=30000, id=42}";
        assertEquals(expected, v.toString());
    }

}
