package fr.laposte.entity.repository;

import fr.laposte.entity.model.Site;
import fr.laposte.entity.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site,Integer> {

    List<Site> findAllByVilleOrderByNomSiteAsc(Ville ville);

}
