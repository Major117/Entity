package com.entity.repository;

import com.entity.model.Entite;
import com.entity.model.Metier;

import java.util.List;

public interface EntiteRepositoryCustom  {

    List<Entite> findByCriteria(String libelle, Metier metier, Boolean rh);



}
