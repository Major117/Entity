package fr.laposte.entity.repository;

import fr.laposte.entity.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville,Integer> {

    List<Ville>  findAllByOrderByNomVilleAsc();
}
