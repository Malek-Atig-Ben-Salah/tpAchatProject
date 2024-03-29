package com.esprit.examen.services;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.Produit;
import  com.esprit.examen.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import  static org.mockito.Mockito.when;
import java.util.*;
@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplTest {
    @InjectMocks
    OperateurServiceImpl operateurService;
    @Mock
    OperateurRepository operateurRepository;
    Operateur operateur;
    Set<Facture> factures;

    @BeforeEach
    void setuP(){
        MockitoAnnotations.initMocks(this);
        operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("ABC123");
        operateur.setPrenom("Test Produit");
        operateur.setPassword("50.0f");
        operateur.setFactures(factures);

    }
    @Test
    void testGettersAndSetters() {
        Assertions.assertEquals("ABC123", operateur.getIdOperateur());
        Assertions.assertEquals("Test", operateur.getNom());
        Assertions.assertEquals("Test", operateur.getPrenom());
        Assertions.assertNotNull(operateur.getPassword());
        Assertions.assertNotNull(operateur.getFactures());
    }
    @Test
    void testFactureAssociation() {
        operateur.setFactures(factures);
        Assertions.assertEquals(factures, operateur.getFactures());
    }

    @Test
    void testRetrieveOperateur() {

        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);

        Mockito.when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));

        Operateur retrievedOperateur = operateurService.retrieveOperateur(1L);

        Assertions.assertNotNull(retrievedOperateur);
        Assertions.assertEquals(1L, retrievedOperateur.getIdOperateur());
    }

    @Test
    void testAddOperateur() {
        Operateur operateur1 = new Operateur();
        when(operateurRepository.save(operateur)).thenReturn(operateur);
        Operateur result = operateurService.addOperateur(operateur);
        Assertions.assertEquals(operateur, result);
    }

}
