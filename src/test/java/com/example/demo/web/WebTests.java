package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.Statistique;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    private Statistique statistique;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetStatistiqueSuccess() throws Exception {
        // Préparation du mock
        Echantillon echantillon = new Echantillon(2, 25000);
        when(statistique.prixMoyen()).thenReturn(echantillon);

        // Exécution de la requête et vérification
        mockMvc.perform(get("/statistique"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDeVoitures").value(2))
                .andExpect(jsonPath("$.prixMoyen").value(25000));
    }

    @Test
    void testGetStatistiqueBadRequest() throws Exception {
        // Simulation de l'exception ArithmeticException (quand il n'y a pas de voitures)
        when(statistique.prixMoyen()).thenThrow(new ArithmeticException());

        // Vérification que le contrôleur renvoie un BAD REQUEST via PasDeVoitureException
        mockMvc.perform(get("/statistique"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPostVoiture() throws Exception {
        Voiture v = new Voiture("Ferrari", 150000);
        
        mockMvc.perform(post("/voiture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(v)))
                .andExpect(status().isOk());

        // On vérifie que la méthode ajouter du service a bien été appelée
        verify(statistique, times(1)).ajouter(any(Voiture.class));
    }
}
