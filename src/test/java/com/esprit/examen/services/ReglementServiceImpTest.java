package com.esprit.examen.services;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;
import com.esprit.examen.repositories.StockRepository;
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
public class ReglementServiceImpTest {
    @InjectMocks
    ReglementServiceImpl reglementServiceImp ;
    private  StockServiceImpl stockService ;
    @Mock
    ReglementRepository reglementRepository  ;
    private StockRepository stockRepository ;

    private List<Reglement> reglements;
    private Reglement reglement1;
    private Reglement reglement2;
    @Mock
    FactureRepository factureRepository ;
    private List<Stock> stocks ;
    private Stock stock1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllReglements() {
        List<Reglement> reglements = new ArrayList<>();
        Reglement reglement1 = new Reglement();
        Reglement reglement2 = new Reglement();
        reglements.add(reglement1);
        reglements.add(reglement2);
        when(reglementRepository.findAll()).thenReturn(reglements);

        List<Reglement> result = reglementServiceImp.retrieveAllReglements();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testAddReglement() {
        // Mocking data
        Reglement reglement = new Reglement();
        when(reglementRepository.save(reglement)).thenReturn(reglement);

        // Call the method to be tested
        Reglement result = reglementServiceImp.addReglement(reglement);

        // Assertions
        Assertions.assertEquals(reglement, result);
    }
    @Test
    void addReglementTest(){
        Mockito.when(reglementRepository.save(reglement1)).thenReturn(reglement1);
        Reglement newReglement = reglementServiceImp.addReglement(reglement1);
        Assertions.assertEquals(newReglement, reglement1);
    }
    @Test
    void retrieveStockTest(){
        Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.ofNullable(stock1));
        Stock retrievedStock = stockService.retrieveStock(1L);
        Assertions.assertEquals(retrievedStock, stock1);
    }
}


