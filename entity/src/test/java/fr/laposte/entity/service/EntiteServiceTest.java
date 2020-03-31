package fr.laposte.entity.service;

import fr.laposte.entity.model.Entite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EntiteServiceTest {

@Autowired
    EntiteService entiteService;


    @Test
    void testAvecUnCodeVide() {

        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.rechercheUneEntiteAvecSonCode("  ");
        });
        assertEquals("La saisie ne respecte pas la syntaxe d'un code Entité (ex : AB1234)", exception.getMessage());
    }

    @Test
    void testAvecUnCodeNull()  {

        Exception exception = assertThrows(Exception.class, () -> {
            entiteService.rechercheUneEntiteAvecSonCode(null);
        });
       assertEquals("La saisie ne respecte pas la syntaxe d'un code Entité (ex : AB1234)", exception.getMessage());


    }

    @Test
    void testAvecUnCodeIncomplet()  {

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
        assertEquals("Confolens",  entiteTest.getVillePhysique().getNomVille());
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


}
