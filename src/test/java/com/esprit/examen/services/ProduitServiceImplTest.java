
package com.esprit.examen.services;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

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
    Set<Produit>produitSet;
    Produit produit;
    @BeforeEach
    void setUp() {
        produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("code");
        produit.setPrix(10);
        produits = new ArrayList<>();
        produits.add(produit);
        produitSet = new HashSet<>();
        produitSet.add(produit);
    }
    @Test
    void retrieveAllProduits (){
        Mockito.when(produitRepository.findAll()).thenReturn(produits);
        List<Produit> produitList = produitService.retrieveAllProduits();
        Assertions.assertEquals(produitList.size() , produits.size());
        Assertions.assertNotEquals(produitList, new ArrayList<>() , "empty list");

    }

    void addProduit(){
        Mockito.when(produitRepository.save(produit)).thenReturn(produit);
        Produit produit1 = produitService.addProduit(produit);
        Assertions.assertEquals(produits , produit1);
    }
    void deleteProduit(){
        produitService.deleteProduit(1L);
        Mockito.verify(produitRepository , Mockito.times(1)).deleteById(1L);
    }
    void updateProduit(){
        Mockito.when(produitRepository.save(produit)).thenReturn(produit);
        Produit produit1 = produitService.updateProduit(produit);
        Assertions.assertEquals(produit , produit1);
    }
    void retrieveProduit(){
        Mockito.when(produitRepository.findById(1L)).thenReturn(Optional.ofNullable(produit));
        Produit produit1 = produitService.retrieveProduit(1L);
        Assertions.assertEquals(produit , produit1);
    }
}
