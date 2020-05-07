package fr.laposte.entity.repository;

import fr.laposte.entity.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface EntiteRepositoryCustom  {

    List<Entite> findByCriteria(String libelle, int metier, int ville, Boolean active, Boolean rh, LocalDateTime date, int activite, int limit);



}
