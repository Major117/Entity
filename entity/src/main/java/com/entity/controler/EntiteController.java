package com.entity.controler;

import com.entity.model.Entite;
import com.entity.repository.EntiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/entite")
@CrossOrigin("*")
public class EntiteController {

    @Autowired
    private EntiteRepository entiteRepository;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<Entite> display() {
        return entiteRepository.findById("AB1337");
    }

}
