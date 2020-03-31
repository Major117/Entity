package fr.laposte.entity.repository;

import fr.laposte.entity.model.Metier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetierRepository extends JpaRepository<Metier, Integer> {
}
