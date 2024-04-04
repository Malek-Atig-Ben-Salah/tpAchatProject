package com.esprit.examen.services;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import com.esprit.examen.services.SecteurActiviteServiceImpl;
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
class SecteurActiviteServiceImplTest {

    @InjectMocks
    private SecteurActiviteServiceImpl secteurActiviteService;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    private SecteurActivite secteurActivite;

    @BeforeEach
    void setUp() {
        secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(1L);
    }

    @Test
    void retrieveAllSecteurActiviteTest() {
        List<SecteurActivite> secteursActivite = new ArrayList<>();
        secteursActivite.add(secteurActivite);

        Mockito.when(secteurActiviteRepository.findAll()).thenReturn(secteursActivite);

        List<SecteurActivite> retrievedSecteursActivite = secteurActiviteService.retrieveAllSecteurActivite();

        Assertions.assertEquals(secteursActivite.size(), retrievedSecteursActivite.size());
        Assertions.assertEquals(secteurActivite, retrievedSecteursActivite.get(0));
    }

    @Test
    void addSecteurActiviteTest() {
        Mockito.when(secteurActiviteRepository.save(Mockito.any(SecteurActivite.class))).thenReturn(secteurActivite);

        SecteurActivite savedSecteurActivite = secteurActiviteService.addSecteurActivite(secteurActivite);

        Assertions.assertNotNull(savedSecteurActivite);
        Assertions.assertEquals(secteurActivite, savedSecteurActivite);
    }

}
