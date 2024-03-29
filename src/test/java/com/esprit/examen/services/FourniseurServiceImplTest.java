package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;
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
public class FourniseurServiceImplTest {
   @InjectMocks
    FournisseurServiceImpl fournisseurService;
  @Mock
    FournisseurRepository fournisseurRepository;
  @Mock
  DetailFournisseurRepository detailFournisseurRepository;
   @Mock
    ProduitRepository produitRepository;
   @Mock
   SecteurActiviteRepository secteurActiviteRepository;
    List<Fournisseur> fournisseurs ;
    Set<Fournisseur> fournisseurSet;
    Fournisseur fournisseur;
@BeforeEach
void setUp(){
    fournisseur=new Fournisseur();
    fournisseur.setIdFournisseur(1L);
    fournisseur.setCode("code");
    fournisseur.setLibelle("libelle");
    fournisseurs =new ArrayList<>();
    fournisseurs.add(fournisseur);
    fournisseurSet=new HashSet<>();
    fournisseurSet.add(fournisseur);
}
   @Test
    void retrieveAllFournisseurs(){
       Mockito.when(fournisseurRepository.findAll()).thenReturn(fournisseurs);
       List<Fournisseur> fournisseurList = fournisseurService.retrieveAllFournisseurs();
       Assertions.assertEquals(fournisseurList.size() , fournisseurs.size());
       Assertions.assertNotEquals(fournisseurList, new ArrayList<>() , "empty list");
   }
   void addFournisseur(){
       Mockito.when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
       Fournisseur fournisseur1 = fournisseurService.addFournisseur(fournisseur);
       Assertions.assertEquals(fournisseur , fournisseur1);
   }
    void deleteFournisseur(){
        fournisseurService.deleteFournisseur(1L);
        Mockito.verify(fournisseurRepository , Mockito.times(1)).deleteById(1L);
    }
    void updateFournisseur(){
        Mockito.when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
        Fournisseur fournisseur1 = fournisseurService.updateFournisseur(fournisseur);
        Assertions.assertEquals(fournisseur , fournisseur);
    }
    void retrieveFournisseur(){
        Mockito.when(fournisseurRepository.findById(1L)).thenReturn(Optional.ofNullable(fournisseur));
        Fournisseur fournisseur1 = fournisseurService.retrieveFournisseur(1L);
        Assertions.assertEquals(fournisseur , fournisseur1);
    }

}
