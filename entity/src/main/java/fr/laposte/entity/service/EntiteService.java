package fr.laposte.entity.service;

import fr.laposte.entity.model.Entite;
import fr.laposte.entity.repository.EntiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional()
public class EntiteService {

    @Autowired
    EntiteRepository entiteRepository;


    public Entite rechercheUneEntiteAvecSonCode(String code) throws Exception {

        Entite entiteEnvoye = new Entite();
        String msg = "";
        if (code != null && code.trim().length() == 6) {
            Optional<Entite> entiteOptional = Optional.empty();
            if (!"AA0000".equals(code)) {
                entiteOptional = entiteRepository.findById(code);
            }
            if (entiteOptional.isPresent()) {
                entiteEnvoye = entiteOptional.get();
                entiteEnvoye.getEntiteMere().getLibelle();

            } else {
                msg = "Le code Entité n'existe pas.";
                throw new Exception(msg);
            }
        } else {
            msg = "La saisie ne respecte pas la syntaxe d'un code Entité (ex : AB1234)";
            throw new Exception(msg);
        }

        return entiteEnvoye;
    }
}
