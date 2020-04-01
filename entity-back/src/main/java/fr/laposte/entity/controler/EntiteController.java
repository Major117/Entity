package fr.laposte.entity.controler;

import fr.laposte.entity.model.Entite;
import fr.laposte.entity.service.EntiteService;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/entite")
@CrossOrigin("*")
public class EntiteController {

    @Bean
    public Jackson2ObjectMapperBuilder configureObjectMapper() {
        return new Jackson2ObjectMapperBuilder().modulesToInstall(Hibernate5Module.class); //
    }

    @Autowired
    private EntiteService es;


    @GetMapping ()
    @ResponseStatus(code = HttpStatus.OK)
    public Entite afficher(@RequestParam (value = "id") String code) throws Exception {

        return es.rechercheUneEntiteAvecSonCode(code) ;
    }

}
