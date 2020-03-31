package fr.laposte.entity.repository;

import fr.laposte.entity.model.Activite;
import fr.laposte.entity.model.Entite;
import fr.laposte.entity.model.Metier;
import fr.laposte.entity.model.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EntiteRepositoryImpl implements EntiteRepositoryCustom {

    @Autowired
    EntityManager em;

   @Override
    public List<Entite> findByCriteria(String libelle, Metier metier, Ville ville, Boolean rh, Activite activite) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Entite> cq = cb.createQuery(Entite.class);

        Root<Entite> entite = cq.from(Entite.class);
        List<Predicate> predicates = new ArrayList<>();



        if (metier != null) {
            predicates.add(cb.equal(entite.get("metier"), metier));
        }
        if (libelle != null) {
            predicates.add(cb.like(entite.get("libelle"), "%" + libelle + "%"));
        }
       if (ville != null) {
           predicates.add(cb.equal(entite.get("villePhysique"), ville));
       }
        if (rh != null) {
            predicates.add(cb.equal(entite.get("rh"), rh));
        }
       if (activite != null) {
           predicates.add(cb.equal(entite.get("activites"), activite));
       }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

}
