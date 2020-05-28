package fr.laposte.entity.service;

import fr.laposte.entity.model.Site;

import fr.laposte.entity.model.Ville;
import fr.laposte.entity.repository.SiteRepository;
import fr.laposte.entity.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SiteService {

    @Autowired
    SiteRepository siteRepository;

    @Autowired
    VilleRepository villeRepository;

    public List<Site> renvoieLesSites(int idVille) {
      Ville ville = new Ville();

         if (villeRepository.findById(idVille).isPresent()) {
          ville = villeRepository.findById(idVille).get();
         }
        System.out.println(siteRepository.findAllByVille(ville).toString());
       return siteRepository.findAllByVille(ville);
    }

}