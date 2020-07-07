package fr.laposte.entity.controller;

import fr.laposte.entity.model.Site;
import fr.laposte.entity.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/init")
@CrossOrigin("*")
public class SiteController {

    @Autowired
    private SiteService ss;

    @GetMapping("/site")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Site> chargeLesSites(@RequestParam (value = "id") int idVille){
        return ss.renvoieLesSites(idVille);
    }
}
