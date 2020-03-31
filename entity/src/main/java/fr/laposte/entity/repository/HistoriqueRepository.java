package fr.laposte.entity.repository;


import fr.laposte.entity.model.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique,Integer> {

}
