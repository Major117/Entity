package fr.laposte.entity.repository;

import fr.laposte.entity.model.Activite;
import fr.laposte.entity.model.Entite;
import fr.laposte.entity.model.Metier;
import fr.laposte.entity.model.Ville;

import java.util.List;

public interface EntiteRepositoryCustom  {

    List<Entite> findByCriteria(String libelle, Metier metier, Ville ville, Boolean rh, Activite activite);



}
