package fr.laposte.entity.controller;

import fr.laposte.entity.dto.CreationForm;
import fr.laposte.entity.dto.RechercheForm;
import fr.laposte.entity.model.*;
import fr.laposte.entity.service.*;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/entite")
@CrossOrigin("*")
public class EntiteController {

    @Bean
    public Jackson2ObjectMapperBuilder configureObjectMapper() {
        return new Jackson2ObjectMapperBuilder().modulesToInstall(Hibernate5Module.class);
    }

    @Autowired
    private EntiteService es;


    @GetMapping("/public/entite-mere")
    @ResponseStatus(code = HttpStatus.OK)
    public boolean analyseCodeEntiteMere(@RequestParam (value = "code") String codeEntite) throws Exception {
        return es.isCodeEntiteValideActive(codeEntite);
    }

    @GetMapping("/public/unique")
    @ResponseStatus(code = HttpStatus.OK)
    public Entite rechercheCodeEntite(@RequestParam (value = "id") String code) throws Exception {
        return es.rechercheUneEntiteAvecSonCode(code) ;
    }

    @PostMapping("/public/multi")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Entite> rechercheMultiCritere(@RequestBody RechercheForm rechercheForm) throws Exception {

        List<Entite> listEntite = new ArrayList<>();

        listEntite = es.multiCritere(rechercheForm.getLibelle(),
                                     rechercheForm.getMetier(),
                                     rechercheForm.getVille(),
                                     rechercheForm.getActive() ,
                                     rechercheForm.getRh(),
                                     rechercheForm.getDate() ,
                                     rechercheForm.getActivite());

        return listEntite;
    }

    @PostMapping("/creation")
    @PreAuthorize("hasRole('Gestionnaire') or hasRole('Admin')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Entite creationEntite(@RequestBody CreationForm creationForm) throws Exception {
       return es.nouvelleEntite(creationForm);

    }

    @PostMapping("/modification")
    @PreAuthorize("hasRole('Gestionnaire') or hasRole('Admin')")
    @ResponseStatus(code = HttpStatus.OK)
    public Entite modificationEntite(@RequestParam String code ,
                                     @RequestBody CreationForm modificationForm) throws Exception {
        return es.udpdateEntite(code,modificationForm);

    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('Admin')")
    @ResponseStatus(code = HttpStatus.OK)
    public void suppressionEntite(@RequestParam String code) throws Exception {
        es.deleteEntite(code);
    }


}
