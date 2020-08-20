package fr.laposte.entity.service;

import fr.laposte.entity.model.Ville;
import fr.laposte.entity.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    VilleRepository villeRepository;

    /**
     * Retourne la liste de toutes les villes.
     * @return
     */
    public List<Ville> revoieToutesLesVilles(String lettre){

        return villeRepository.autoCompleteVille(lettre);
    }
}
