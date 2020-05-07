package fr.laposte.entity.repository;

import fr.laposte.entity.model.Entite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EntiteRepository extends JpaRepository<Entite, String> , EntiteRepositoryCustom {

    @Query("SELECT MAX(e.codeEntite) FROM Entite e")
    String findLastCodeEntite();

}
