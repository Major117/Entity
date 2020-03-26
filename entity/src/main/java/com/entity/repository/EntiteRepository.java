package com.entity.repository;

import com.entity.model.Entite;
import com.entity.model.Historique;
import com.entity.model.Metier;
import com.entity.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EntiteRepository extends JpaRepository<Entite, String> , EntiteRepositoryCustom {


}
