package fr.laposte.entity.controller;

import fr.laposte.entity.model.Ville;
import fr.laposte.entity.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/init")
@CrossOrigin("*")
public class VilleController {

    @Autowired
    private VilleService vs;

    @GetMapping("/ville")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Ville> chargeLesVilles(){
        return vs.revoieToutesLesVilles();
    }
}
