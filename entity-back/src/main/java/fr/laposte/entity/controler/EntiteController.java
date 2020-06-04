package fr.laposte.entity.controler;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.laposte.entity.model.*;
import fr.laposte.entity.service.*;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/entite")
@CrossOrigin("*")
public class EntiteController {

    @Bean
    public Jackson2ObjectMapperBuilder configureObjectMapper() {
        return new Jackson2ObjectMapperBuilder().modulesToInstall(Hibernate5Module.class); //TODO
    }

    @Autowired
    private EntiteService es;

    @Autowired
    private MetierService ms;

    @Autowired
    private ActiviteService as;

    @Autowired
    private VilleService vs;

    @Autowired
    private SiteService ss;


    @GetMapping("/init-metier")
    @ResponseStatus(code = HttpStatus.OK)
     public List<Metier> chargeLesMetiers(){
         return ms.revoieTousLesMetier();
     }

    @GetMapping("/init-ville")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Ville> chargeLesVilles(){
        return vs.revoieToutesLesVilles();
    }

    @GetMapping("/init-activite")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Activite> chargeLesActivites(){
        return as.revoieTousLesActivites();
    }

    @GetMapping("/init-site")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Site> chargeLesSites(@RequestParam (value = "id") int idVille){
        return ss.renvoieLesSites(idVille);
    }

    @GetMapping("/entite-mere")
    @ResponseStatus(code = HttpStatus.OK)
    public boolean analyseCodeEntiteMere(@RequestParam (value = "code") String codeEntite) throws Exception {
        return es.isCodeEntiteValideActive(codeEntite);
    }

    @GetMapping("/unique")
    @ResponseStatus(code = HttpStatus.OK)
    public Entite rechercheCodeEntite(@RequestParam (value = "id") String code) throws Exception {
        return es.rechercheUneEntiteAvecSonCode(code) ;
    }

    @PostMapping("/multi")
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
        System.out.println(modificationForm.getVoieAdressePostal());
        return es.udpdateEntite(code,modificationForm);

    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('Admin')")
    @ResponseStatus(code = HttpStatus.OK)
    public void suppressionEntite(@RequestParam String code) throws Exception {
        es.deleteEntite(code);
    }


}
