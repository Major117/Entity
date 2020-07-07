package fr.laposte.entity.controller;

import fr.laposte.entity.model.Activite;
import fr.laposte.entity.service.ActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/init")
@CrossOrigin("*")
public class ActiviteController {

    @Autowired
    private ActiviteService as;

    @GetMapping("/activite")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Activite> chargeLesActivites(){
        return as.revoieTousLesActivites();
    }


}
