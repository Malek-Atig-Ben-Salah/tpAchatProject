package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategorieProduitServiceImplTest {

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;
    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    private List<CategorieProduit> categorieProduits;
    private CategorieProduit categorieProduit1;
    private CategorieProduit categorieProduit2;
    @BeforeEach
    void setUp(){
        categorieProduit1 = CategorieProduit.builder().
                idCategorieProduit(1L).
                codeCategorie("codeCategorie").
                libelleCategorie("libelleCategorie").
                build();
        categorieProduit2 = CategorieProduit.builder().
                idCategorieProduit(2L).
                codeCategorie("codeCategorie2").
                libelleCategorie("libelleCategorie2").
                build();
        categorieProduits = new ArrayList<>();
        categorieProduits.add(categorieProduit1);
        categorieProduits.add(categorieProduit2);
    }
    @Test
    void retrieveAllCategorieProduitsTest(){
        Mockito.when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);
        List<CategorieProduit> categorieProduitList = categorieProduitService.retrieveAllCategorieProduits();
        Assertions.assertEquals(categorieProduitList.size() , categorieProduits.size());
        Assertions.assertNotEquals(categorieProduitList, new ArrayList<>() , "empty list");
    }
    @Test
    void addCategorieProduitTest(){
        Mockito.when(categorieProduitRepository.save(categorieProduit1)).thenReturn(categorieProduit1);
        CategorieProduit categorieProduit = categorieProduitService.addCategorieProduit(categorieProduit1);
        Assertions.assertEquals(categorieProduit , categorieProduit1);
    }
    @Test
    void deleteCategorieProduitTest(){
        categorieProduitService.deleteCategorieProduit(1L);
        Mockito.verify(categorieProduitRepository , Mockito.times(1)).deleteById(1L);
    }
    @Test
    void updateCategorieProduitTest(){
        Mockito.when(categorieProduitRepository.save(categorieProduit1)).thenReturn(categorieProduit1);
        CategorieProduit categorieProduit = categorieProduitService.updateCategorieProduit(categorieProduit1);
        Assertions.assertEquals(categorieProduit , categorieProduit1);
    }
    @Test
    void retrieveCategorieProduitTest(){
        Mockito.when(categorieProduitRepository.findById(1L)).thenReturn(Optional.ofNullable(categorieProduit1));
        CategorieProduit categorieProduit = categorieProduitService.retrieveCategorieProduit(1L);
        Assertions.assertEquals(categorieProduit , categorieProduit1);
    }
 //C moi qui l'a ajouté
    @Test
    void deleteCategorieProduitTest(){
        categorieProduitService.deleteCategorieProduit(1L);
        Mockito.verify(categorieProduitRepository , Mockito.times(1)).deleteById(1L);
    }
    @Test
    void updateCategorieProduitTest(){
        Mockito.when(categorieProduitRepository.save(categorieProduit1)).thenReturn(categorieProduit1);
        CategorieProduit categorieProduit = categorieProduitService.updateCategorieProduit(categorieProduit1);
        Assertions.assertEquals(categorieProduit , categorieProduit1);
    }
}
