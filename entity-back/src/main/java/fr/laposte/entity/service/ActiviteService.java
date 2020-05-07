package fr.laposte.entity.service;

import fr.laposte.entity.model.Activite;
import fr.laposte.entity.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiviteService {

    @Autowired
    ActiviteRepository activiteRepository;

    public List<Activite> revoieTousLesActivites(){

        return  activiteRepository.findAll();
    }
}
