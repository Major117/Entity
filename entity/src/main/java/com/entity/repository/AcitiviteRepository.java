package com.entity.repository;

import com.entity.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcitiviteRepository extends JpaRepository<Activite, Integer> {

}
