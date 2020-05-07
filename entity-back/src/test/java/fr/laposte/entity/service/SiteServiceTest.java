package fr.laposte.entity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SiteServiceTest {

@Autowired
    SiteService siteService;

   @Test
    void testListeDeSiteAvecVille() {

      assertEquals(1, siteService.renvoieLesSites(2).size());
   }
}
