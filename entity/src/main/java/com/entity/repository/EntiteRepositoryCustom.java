package com.entity.repository;

import com.entity.model.Entite;

import java.util.List;

public interface EntiteRepositoryCustom  {

    List<Entite> findByCriteria(String libelle, String cpPhysique);



}
