package fr.laposte.entity.repository;

import fr.laposte.entity.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville,Integer> {

    List<Ville>  findAllByOrderByNomVilleAsc();

     @Query("SELECT v FROM Ville v WHERE v.nomVille LIKE CONCAT(:lettre, '%') ORDER BY v.nomVille asc")
    List<Ville> autoCompleteVille(String lettre);

}
