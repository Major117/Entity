package com.entity.repository;


import com.entity.model.*;
import com.entity.repository.EntiteRepository;
import com.entity.repository.MetierRepository;
import com.entity.repository.SiteRepository;
import com.entity.repository.VilleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EntiteRepositoryTest {

    @Autowired //
            EntiteRepository entiteRepository;
    @Autowired
            VilleRepository villeRepository;
    @Autowired
            SiteRepository siteRepository;
    @Autowired
            MetierRepository metierRepository;



    /**
     *
     */
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
        } else {
            fail("Entite AA0000 non trouvée");
        }
    }

    @Test
    void testRecuperationDesDonneesEntitee() {
        Entite entiteTest = entiteRepository.findById("AB1337").get();

        assertNotNull(entiteTest);
        assertEquals("AB1337", entiteTest.getCodeEntite());
        assertEquals("PDC Confolens", entiteTest.getLibelle());
        assertEquals(true, entiteTest.isRh());
        assertEquals(false, entiteTest.isComptable());
        assertEquals("le mas félix", entiteTest.getVoiePhysique());
        assertEquals("16500", entiteTest.getCpPhysique());
        assertEquals("le mas félix", entiteTest.getVoiePostale());
        assertEquals("16500", entiteTest.getCpPostale());
        assertEquals(16, entiteTest.getSite().getCodeSite());
        assertEquals("Confolens", entiteTest.getSite().getNomSite());
        assertEquals("courrier", entiteTest.getMetier().getNomMetier());
        assertEquals("PA2759", entiteTest.getEntiteMere().getCodeEntite());
        assertEquals("Siège social", entiteTest.getEntiteMere().getLibelle());
        assertEquals("Confolens", entiteTest.getVillePhysique().getNomVille());
        assertEquals("Confolens", entiteTest.getVillePostale().getNomVille());
        assertEquals(3, entiteTest.getActivites().size());
        assertEquals(1, entiteTest.getHistoriques().size());

    }



    @Test
    /**
     *
     */
    void testSaveEntite() {
        Metier metier = metierRepository.findById(1).get();
        Ville ville = villeRepository.findById(2).get();
        Site site = siteRepository.findById(3).get();
        Entite mere = entiteRepository.findById("AA0000").get();


        Entite entiteTest = new Entite ("AA1234","juste test", false,false,"test voie","16500","test voie","16500",metier,site,ville,ville,mere,null, null);
        entiteRepository.save(entiteTest);
        Entite entiteTest2 = entiteRepository.findById("AA1234").get();
        assertNotNull(entiteTest);
        assertEquals(entiteTest2.getCodeEntite(), entiteTest.getCodeEntite());
        assertEquals(entiteTest2.getVillePhysique(), entiteTest.getVillePhysique());
    }

    @Test
    /**
     *
     */
     void testDeleteEntiteId() {

        Metier metier = metierRepository.findById(1).get();
        Ville ville = villeRepository.findById(2).get();
        Site site = siteRepository.findById(3).get();
        Entite mere = entiteRepository.findById("AA0000").get();


        Entite entiteTest = new Entite("AA1237", "PDC confolens", true, true, "test voie" , "16500", "test voie" , "16500", metier, site, ville,ville, mere,null, null);

        entiteRepository.save(entiteTest);
        entiteRepository.deleteById(entiteTest.getCodeEntite());

    }

    @Test
    /**
     *
     */
    void testRechercheMultiCritere() {
        Metier metier = metierRepository.findById(3).get();

        List<Entite> testRecherche = entiteRepository.findByCriteria("Pag",metier,null);
         assertEquals(1, testRecherche.size());

    }


}
