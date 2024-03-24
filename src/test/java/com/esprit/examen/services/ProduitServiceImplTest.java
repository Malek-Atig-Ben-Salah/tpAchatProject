package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
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
public class ProduitServiceImplTest {
    @InjectMocks
    ProduitServiceImpl produitService;
    @Mock
    ProduitRepository produitRepository;
    @Mock
    StockRepository stockRepository;
    @Mock
    CategorieProduitRepository categorieProduitRepository;

    List<Produit> produits;
    Set<DetailFacture> detailFactures;
    Produit produit;
    CategorieProduit categorieProduit;
    @BeforeEach
    void setuP(){
        MockitoAnnotations.initMocks(this);
        produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("ABC123");
        produit.setLibelleProduit("Test Produit");
        produit.setPrix(50.0f);
        produit.setDateCreation(new Date());
        produit.setDateDerniereModification(new Date());
    }
    @Test
    void testGettersAndSetters() {
       // Assertions.assertEquals(1L, produit.getIdProduit());
        Assertions.assertEquals("ABC123", produit.getCodeProduit());
        Assertions.assertEquals("Test Produit", produit.getLibelleProduit());
        //Assertions.assertEquals(50.0f, produit.getPrix());
        Assertions.assertNotNull(produit.getDateCreation());
        Assertions.assertNotNull(produit.getDateDerniereModification());
    }
    @Test
    void testStockAssociation() {
        produit.setDetailFacture(detailFactures);
        Assertions.assertEquals(detailFactures, produit.getDetailFacture());
    }
    @Test
    void testCategorieProduitAssociation() {
        produit.setCategorieProduit(categorieProduit);
        Assertions.assertEquals(categorieProduit, produit.getCategorieProduit());
    }
    @Test
    void testRetrieveAllProduits() {
        List<Produit> produits = new ArrayList<>();
        Produit produit1 = new Produit();
        Produit produit2 = new Produit();
        produits.add(produit1);
        produits.add(produit2);
        when(produitRepository.findAll()).thenReturn(produits);
        List<Produit> result = produitService.retrieveAllProduits();
        Assertions.assertEquals(2, result.size());
    }
    @Test
    void testAddProduit() {
        Produit produit = new Produit();
        when(produitRepository.save(produit)).thenReturn(produit);
        Produit result = produitService.addProduit(produit);
        Assertions.assertEquals(produit, result);
    }
}
