package com.entity.service;

import com.entity.model.Entite;
import com.entity.repository.EntiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EntiteService {

    @Autowired
    EntiteRepository entiteRepository;


    public Entite rechercheUneEntiteAvecSonCode(String code) {

        Entite entite = entiteRepository.findById(code).get();
        return entite;
    }


}
