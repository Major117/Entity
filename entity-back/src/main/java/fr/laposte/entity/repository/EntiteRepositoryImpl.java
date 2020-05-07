package fr.laposte.entity.repository;

import fr.laposte.entity.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Repository
public class EntiteRepositoryImpl implements EntiteRepositoryCustom {

    @Autowired
    EntityManager em;

    @Autowired
    ActiviteRepository activiteRepository;


    @Override
    public List findByCriteria(String libelle, int metier, int ville, Boolean active , Boolean rh, LocalDateTime date, int activite, int limit) {
       String entiteTechnique = "AA0000";

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Entite> cq = cb.createQuery(Entite.class);

        Root<Entite> entite = cq.from(Entite.class);
        List<Predicate> predicates = new ArrayList<>();

        if (libelle != null && libelle.trim().length() >= 3) {
            predicates.add(cb.like(entite.get("libelle"), "%" + libelle + "%"));
        }
        if (metier != 0) {
            predicates.add(cb.equal(entite.get("metier"), metier));
        }
        if (ville != 0) {
            predicates.add(cb.equal(entite.get("villePhysique"), ville));
        }
        if (active != null) {

            Join<Entite, Historique> join = entite.join("historiques", JoinType.INNER);

            Subquery<LocalDateTime> sousRequeteHistorique = cb.createQuery()
                    .subquery(LocalDateTime.class);
            Root<Historique> from = sousRequeteHistorique.from(Historique.class);
            // date la plus recente des historiques de l'entite
            sousRequeteHistorique.select(cb.greatest(from.<LocalDateTime>get("date")))
                    .where(cb.equal(from.<String> get("codeEntite"),
                            entite.<String> get("codeEntite")));

            Predicate derniereOperation =
                    cb.and(cb.equal(join.get("date"), sousRequeteHistorique));
            Predicate operationActive = cb.and(cb.notEqual(join.get("operation"), "D"));
            Predicate operationInactive = cb.and(cb.equal(join.get("operation"), "D"));

            if (active) {
                predicates.add(cb.and(derniereOperation, operationActive));
            } else {
                predicates.add(cb.and(derniereOperation, operationInactive));
            }


        }
        if (rh != null) {
            predicates.add(cb.equal(entite.get("rh"), rh));
        }
        if (date != null) {
            Join<Object, Object> historiques = entite.join("historiques", JoinType.INNER);
            predicates.add(cb.equal(historiques.get("operation"), "C"));
            predicates.add(cb.greaterThanOrEqualTo(historiques.get("date"), date));

        }
        if (activite != 0) {
            Activite activiteRecherche = activiteRepository.findById(activite).get();
            predicates.add(cb.isMember(activiteRecherche, entite.get("activites")));
        }
        predicates.add(cb.notEqual(entite.get("codeEntite"), entiteTechnique));
        cq.where(predicates.toArray(new Predicate[0]));

        Query query ;

        if (limit == 0) {
            query = em.createQuery(cq);
        } else {
           query = em.createQuery(cq).setMaxResults(limit);
        }

        return query.getResultList();
    }

}
