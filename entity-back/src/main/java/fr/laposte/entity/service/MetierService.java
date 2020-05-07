package fr.laposte.entity.service;

import fr.laposte.entity.model.Metier;
import fr.laposte.entity.repository.MetierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetierService {

    @Autowired
    MetierRepository metierRepository;

    public List<Metier> revoieTousLesMetier(){

        return  metierRepository.findAll();
    }

}
