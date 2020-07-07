package fr.laposte.entity.controller;

import fr.laposte.entity.model.Metier;
import fr.laposte.entity.service.MetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/init")
@CrossOrigin("*")
public class MetierController {

    @Autowired
    private MetierService ms;

    @GetMapping("/metier")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Metier> chargeLesMetiers(){
        return ms.revoieTousLesMetier();
    }
}
