package com.esprit.examen.services;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import com.esprit.examen.services.StockServiceImpl;
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
public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setIdStock(1L);
        // Initialisation d'autres propriétés du stock...
    }

    @Test
    void retrieveAllStocksTest() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(stock);

        Mockito.when(stockRepository.findAll()).thenReturn(stocks);

        List<Stock> retrievedStocks = stockService.retrieveAllStocks();

        Assertions.assertEquals(stocks.size(), retrievedStocks.size());
        Assertions.assertEquals(stock, retrievedStocks.get(0));
    }

    @Test
    void addStockTest() {
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);

        Stock savedStock = stockService.addStock(stock);

        Assertions.assertNotNull(savedStock);
        Assertions.assertEquals(stock, savedStock);
    }

}
