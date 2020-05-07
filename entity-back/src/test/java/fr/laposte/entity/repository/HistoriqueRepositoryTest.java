package fr.laposte.entity.repository;

import fr.laposte.entity.model.Historique;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HistoriqueRepositoryTest {

    @Autowired
    HistoriqueRepository historiqueRepository;

    @Autowired
    EntiteRepository entiteRepository;

    @Test
    void testDonnéeOperationEtDateEntite() {

        Historique historiqueTest = historiqueRepository.findById(5).get();


       assertEquals("C", historiqueTest.getOperation());
       assertEquals("22-01-2016 02:37:00", historiqueTest.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));

    }

    @Test
    void testIdentiteDuGestionaire() {

        Historique historiqueTest = historiqueRepository.findById(5).get();

        assertEquals("CJG972", historiqueTest.getLogin());
        assertEquals("Dupont", historiqueTest.getNom());
        assertEquals("Florian", historiqueTest.getPrenom());

    }


    @Test
    void testEntiteActive() {
       String codeEntite = "AB1337";
       String codeEntite2 = "CA5458";
        assertEquals("AB1337", historiqueRepository.entiteActive(codeEntite));
        assertNull(historiqueRepository.entiteActive(codeEntite2));
    }
}
