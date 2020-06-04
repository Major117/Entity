package fr.laposte.entity.repository;

import fr.laposte.entity.model.Entite;
import fr.laposte.entity.model.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique,Integer> {

    @Query( "SELECT h.codeEntite.codeEntite " +
            "FROM Historique h " +
            "WHERE h.date =(SELECT max(h.date) FROM Historique h WHERE h.codeEntite.codeEntite = ?1 )" +
            "AND h.codeEntite.codeEntite = ?1 " +
            "AND h.operation <> 'D'")
    String entiteActive(String codeEntite);

    @Query("DELETE FROM Historique h WHERE h.codeEntite.codeEntite = ?1 ")
    @Modifying
    void removeHistoriques(String code);

    void removeHistoriquesByCodeEntite_CodeEntite(String code);
}
