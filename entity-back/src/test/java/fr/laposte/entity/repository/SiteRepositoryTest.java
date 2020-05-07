package fr.laposte.entity.repository;

import fr.laposte.entity.model.Ville;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SiteRepositoryTest {

    @Autowired
    SiteRepository siteRepository;

   @Test
   void testFindAllByVille() {
       Ville ville = new Ville();
        ville.setIdVille(2);
        ville.setNomVille("Confolens");

       assertEquals(1,siteRepository.findAllByVille(ville).size());
   }
}
