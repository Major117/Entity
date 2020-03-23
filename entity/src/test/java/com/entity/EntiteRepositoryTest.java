package com.entity;


import com.entity.model.*;
import com.entity.repository.EntiteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EntiteRepositoryTest {

    @Autowired //
            EntiteRepository entiteRepository;

    @Test
    void testCodeEntiteInexistante() {
        Optional<Entite> entiteT = entiteRepository.findById("AB13");

        assertFalse(entiteT.isPresent());
    }

    @Test
    /**
     *
     */
    void testRechercheCodeEntiteTechnique() {
        Optional<Entite> entiT = entiteRepository.findById("AA0000");

        if (entiT.isPresent()) {
            assertEquals("AA0000", entiT.get().getCodeEntite());
            System.out.println(entiteRepository.findById("AA0000"));
        } else {
            fail("Entite AA0000 non trouvée");
        }
    }

    @Test
    void testSaveEntite() {

        Entite entite = new Entite ("AA1234","juste test", false,false,"test voie" ,"16500","test voie" ,"16500",6,6,0,0,"AA0000" );
        entiteRepository.save(entite);
        Entite entite2 = entiteRepository.findById("AA4587");
        assertNotNull(entite);
        assertEquals(entite2.getMetier(), entite.getMetier()); //le code Entite
        assertEquals(entite2.getEntiteMere(), entite.getEntiteMere());
    }

    @Test
     void deleteEntiteIdTest() {
        Entite entite = new Entite("AA000","PDC confolens",true,true,"test voie" ,"16500","test voie" ,"16500",  new Metier(1,"courrier"), new Site(1,16, "Confolens",new Ville(1, "Confolens")), new Ville(1, "Confolens"), new Ville(1, "Confolens"), new Optional<Entite>(), ); //("AA1234","juste test", 0,0,"test voie" ,"16500","test voie" ,"16500",6,6,"AA0000",0,0 );
        Entite ent = entiteRepository.save(entite);
        entiteRepository.deleteById(ent.getCodeEntite());

    }

}
