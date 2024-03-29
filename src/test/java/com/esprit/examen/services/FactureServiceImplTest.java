package com.esprit.examen.services;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.*;
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

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class FactureServiceImplTest {

    @InjectMocks
    FactureServiceImpl factureService;
    @Mock
    FactureRepository factureRepository;
    @Mock
    OperateurRepository operateurRepository;
    @Mock
    DetailFactureRepository detailFactureRepository;
    @Mock
    FournisseurRepository fournisseurRepository;
    @Mock
    ProduitRepository produitRepository;
    @Mock
    ReglementServiceImpl reglementService;

    List<Facture> factures ;
    Set<Facture> factureSet;
    Facture facture;
    Operateur operateur;
    Fournisseur fournisseur;
    @BeforeEach
    void setUp(){

        facture = new Facture();
        facture.setArchivee(true);
        facture.setIdFacture(1L);
        facture.setMontantRemise(15);
        facture.setDateDerniereModificationFacture(new Date());
        factures = new ArrayList<>();
        factures.add(facture);
        factureSet = new HashSet<>();
        factureSet.add(facture);
        fournisseur = new Fournisseur();
        fournisseur.setFactures(factureSet);
        fournisseur.setIdFournisseur(1L);
        fournisseur.setCode("code");
        fournisseur.setLibelle("libelle");

        operateur = new Operateur();
        operateur.setNom("nom");
        operateur.setPrenom("prenom");
        operateur.setPassword("password");
        operateur.setFactures(factureSet);
        operateur.setIdOperateur(1L);
    }

    @Test
    void retrieveAllFacturesTest(){
        Mockito.when(factureRepository.findAll()).thenReturn(factures);
        List<Facture> factureList = factureService.retrieveAllFactures();
        Assertions.assertEquals(factures.size() , factureList.size());
    }

    @Test
    void addFactureTest(){
        Mockito.when(factureRepository.save(facture)).thenReturn(facture);
        Facture facture1 = factureService.addFacture(facture);
        Assertions.assertEquals(facture , facture1);
    }

    @Test
    void cancelFactureTest() {
        Mockito.when(factureRepository.findById(1L)).thenReturn(Optional.ofNullable(facture));
        Mockito.when(factureRepository.save(facture)).thenReturn(facture);
        Mockito.verify(factureRepository, times(0)).updateFacture(1L);
        factureService.cancelFacture(1L);
    }

    @Test
    void assignOperateurToFactureTest(){
        Mockito.when(factureRepository.findById(1L)).thenReturn(Optional.ofNullable(facture));
        Mockito.when(operateurRepository.findById(1L)).thenReturn(Optional.ofNullable(operateur));
        Mockito.when(operateurRepository.save(operateur)).thenReturn(operateur);
        factureService.assignOperateurToFacture(1L , 1L);
    }

    @Test
    void pourcentageRecouvrementTest(){
        Date date = new Date();
        Mockito.when(factureRepository.getTotalFacturesEntreDeuxDates(date , date)).thenReturn(15F);
        Mockito.when(reglementService.getChiffreAffaireEntreDeuxDate(date , date)).thenReturn(15F);
        factureService.pourcentageRecouvrement(date , date);
    }
}
