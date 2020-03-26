package com.entity.controler;

import com.entity.model.Entite;
import com.entity.repository.EntiteRepository;
import com.entity.service.EntiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/entite")
@CrossOrigin("*")
public class EntiteController {

    @Autowired
    private EntiteService es;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Entite display(String str) {
        return es.rechercheUneEntiteAvecSonCode(str);
    }

}
