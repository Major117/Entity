package fr.laposte.entity.service;

import fr.laposte.entity.model.*;
import fr.laposte.entity.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EntiteServiceTest {

    @Autowired
    EntiteService entiteService;

    @Autowired
    EntiteRepository entiteRepository;

    @Autowired
    VilleRepository villeRepository;

    @Autowired
    SiteRepository siteRepository;

    @Autowired
    MetierRepository metierRepository;

    @Autowired
    ActiviteRepository activiteRepository;


    @Test
    void testAvecUnCodeVide() {

        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.rechercheUneEntiteAvecSonCode("  ");
        });
        assertEquals("La saisie ne respecte pas la syntaxe d'un code Entité (ex : AB1234)", exception.getMessage());
    }

    @Test
    void testAvecUnCodeNull() {

        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.rechercheUneEntiteAvecSonCode(null);
        });
        assertEquals("La saisie ne respecte pas la syntaxe d'un code Entité (ex : AB1234)", exception.getMessage());


    }

    @Test
    void testAvecUnCodeIncomplet() {

        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.rechercheUneEntiteAvecSonCode("Ac457");
        });
        assertEquals("La saisie ne respecte pas la syntaxe d'un code Entité (ex : AB1234)", exception.getMessage());

    }

    @Test
    void testAvecCodeExistant() {

        Entite entiteTest = null;
        try {
            entiteTest = entiteService.rechercheUneEntiteAvecSonCode("AB1337");
        } catch (Exception e) {
            fail("Erreur");
        }

        assertEquals("Siège social", entiteTest.getEntiteMere().getLibelle());
        assertEquals("Confolens", entiteTest.getVillePhysique().getNomVille());
    }

    @Test
    void testAvecCodeNonExistant() {
        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.rechercheUneEntiteAvecSonCode("AC0006");
        });
        assertEquals("Le code Entité n'existe pas.", exception.getMessage());
    }

    @Test
    void testAvecCodeEntiteTechnique() {
        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.rechercheUneEntiteAvecSonCode("AA0000");
        });
        assertEquals("Le code Entité n'existe pas.", exception.getMessage());
    }

    @Test
    void testAvecUnResultatSansParametre() {
        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.multiCritere(null, 0, 0, null, null, null, 0);
        });
        assertEquals("Veuillez au moins saisir un paramètre de recherche.", exception.getMessage());
    }

    @Test
    void testAvecUnResultatVide() {
        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.multiCritere("java", 4, 2, false, false, null, 1);
        });
        assertEquals("Aucune entité trouvée pour le(s) critère(s) saisi(s).", exception.getMessage());
    }

    @Test
    void testAvecUnResultatCentParametre() throws Exception {
        List<Entite> listeEntite = new ArrayList<>();

        listeEntite = entiteService.multiCritere(null, 0, 0, true, null, null, 0);

        assertEquals(100, listeEntite.size());
    }

    @Test
    void testcodeEntitéUniqueAA1589() {
        String test = entiteService.genereUnCodeEntiteUnique("AA1589");
        assertEquals("AA1590", test);
    }

    @Test
    void testcodeEntitéUniqueAA0068() {
        String test = entiteService.genereUnCodeEntiteUnique("AA0068");
        assertEquals("AA0069", test);
    }

    @Test
    void testcodeEntitéUniqueAA9999() {
        String test = entiteService.genereUnCodeEntiteUnique("AA9999");
        assertEquals("AB0000", test);
    }

    @Test
    void testcodeEntitéUniqueAZ9999() {
        String test = entiteService.genereUnCodeEntiteUnique("AZ9999");
        assertEquals("BA0000", test);
    }


    @Test
    void testCodeEntiMereActive() throws Exception {
        boolean test = entiteService.isCodeEntiteValideActive("AB1337");
        assertTrue(test);
    }

    @Test
    void testCodeEntiMereDesactive() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.isCodeEntiteValideActive("CA5458");
        });
        assertEquals("Code entité inactive", exception.getMessage());
    }

    @Test
    void creerUneEntite() throws Exception {
        Metier metier = metierRepository.findById(1).get();
        Ville ville = villeRepository.findById(2).get();
        Site site = siteRepository.findById(3).get();
        Entite mere = entiteRepository.findById("AB1337").get();
        Activite activite = activiteRepository.findById(2).get();


        CreationForm entiteTest = new CreationForm();

        //TODO
        entiteService.nouvelleEntite(entiteTest);
    }
}
