package fr.laposte.entity.service;

import fr.laposte.entity.model.*;
import fr.laposte.entity.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional()
public class EntiteService {

    @Autowired
    EntiteRepository entiteRepository;

    @Autowired
    EntiteRepositoryImpl entiteRepoImpl;

    @Autowired
    HistoriqueRepository historiqueRepository;

    @Autowired
    MetierRepository metierRepository;

    @Autowired
    SiteRepository siteRepository;

    @Autowired
    VilleRepository villeRepository;

    @Autowired
    ActiviteRepository activiteRepository;


    public Entite rechercheUneEntiteAvecSonCode(String code) throws Exception {

        Entite entiteEnvoye = new Entite();
        String msg = "";
        if (code != null && code.trim().length() == 6) {
            Optional<Entite> entiteOptional = Optional.empty();
            if (!"AA0000".equals(code.toUpperCase())) {
                entiteOptional = entiteRepository.findById(code.toUpperCase());
            }
            if (entiteOptional.isPresent()) {
                entiteEnvoye = entiteOptional.get();
                entiteEnvoye.getEntiteMere().getLibelle();

            } else {
                msg = "Le code Entité n'existe pas.";
                throw new Exception(msg);
            }
        } else {
            msg = "La saisie ne respecte pas la syntaxe d'un code Entité (ex : AB1234)";
            throw new Exception(msg);
        }

        return entiteEnvoye;
    }


    public List<Entite> multiCritere (String libelle,
                                      int metier,
                                      int ville,
                                      Boolean active,
                                      Boolean rh,
                                      Date date,
                                      int activite) throws Exception {

        List<Entite> listEntite = new ArrayList<>();
        LocalDateTime formatDate = null;
        String msg = "";
        int limit = 100;

    if (date != null) {
         formatDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    if ((libelle == null || libelle.isEmpty()) && metier == 0 && ville == 0 && active == null && rh == null && date == null && activite == 0 ) {
        msg = "Veuillez au moins saisir un paramètre de recherche.";
        throw new Exception(msg);
    }
    if (!libelle.isEmpty() && libelle.trim().length() < 3) {
            msg = "Le libellé doit faire minimum 3 caractères";
            throw new Exception(msg);
    }

    listEntite = entiteRepoImpl.findByCriteria(libelle, metier, ville, active, rh, formatDate, activite, limit);

    if (listEntite.size() == 0)  {
        msg = "Aucune entité trouvée pour le(s) critère(s) saisi(s).";
        throw new Exception(msg);
    }

      return listEntite;
    }


    public boolean isCodeEntiteValideActive(String codeEntite) throws Exception { //TODO...

        rechercheUneEntiteAvecSonCode(codeEntite); //Vérifie le contrôle de saisie et sa présence en BDD, en réutilisant la méthode rechercheUneEntiteAvecSonCode().
        String active = historiqueRepository.entiteActive(codeEntite);
        String msg = "";

        if (active != null) {  //verifie si le code est active
            return true;
        } else {
            msg = "Code entité inactive";
            throw new Exception(msg);
        }

    }

    /**
     * Génère Un code Entité unique
     * @return
     */
    public String genereUnCodeEntiteUnique(String lastCode) {

        String newCodeEntite ;
        String lettre ="ABCDEFGHJKLMNPQRSTUVWXYZ";
        String zero = "0";

        String first = lastCode.substring(0,1);
        String two = lastCode.substring(1,2);
        int num = Integer.parseInt(lastCode.substring(2));

        String finalNum;
        int compteur;

        if (num < 9999) {
           num++;
           finalNum = String.valueOf(num);
           while (finalNum.length() != 4) {
               finalNum = zero + finalNum;
           }
       } else if (!two.equals("Z")) {
           finalNum = "0000";
          compteur = lettre.indexOf(two);
          compteur ++;
          two = String.valueOf(lettre.charAt(compteur));
       } else {
           finalNum = "0000";
           two = "A";
           compteur = lettre.indexOf(first);
           compteur++;
           first = String.valueOf(lettre.charAt(compteur));
       }

       newCodeEntite = first+two+finalNum;

        return newCodeEntite;
    }


    public Entite nouvelleEntite (CreationForm form) throws Exception {

        Entite entiteEnvoye = new Entite();
        String msg = "";
        String lastCode = entiteRepository.findLastCodeEntite();
        String newCode ;

        String regex = "[a-zA-Z0-9_.()&@!?%:*€'àâäæçéèêëîïôœùûü -]*";
        Pattern pattern = Pattern.compile(regex);

        entiteEnvoye.setRh(form.isRh()); // Rh
        entiteEnvoye.setComptable(form.isComptable()); //Comptable

        // Code Entite
        if (!lastCode.equals("ZZ9999")) {                     //TODO mettre les controles dans une methode différente
            newCode = genereUnCodeEntiteUnique(lastCode);
          entiteEnvoye.setCodeEntite(newCode);
        } else {
            msg = "plus de code disponible !";
            throw new Exception(msg);
        }

        // Libelle
        if (form.getLibelle() != null ) {
            String libelle = form.getLibelle();
            Matcher matcher = pattern.matcher(libelle);
            if (libelle.length() <= 50 && libelle.length() >= 3) {
                if (matcher.matches()) {
                    entiteEnvoye.setLibelle(libelle);
                } else {
                    msg = "Seuls les caractères alphanumériques accentués sont autorisés et les caractères spéciaux suivant : () - & + @ !? * . % : € <espace> et <apostrophe>";
                    throw new Exception(msg);
                }
            } else {
                msg = "Le Libellé doit contenir 3 caractères minimun et 50 maximun";
                throw new Exception(msg);
            }
        } else {
            msg = "Le libellé est obligatoire";
            throw new Exception(msg);
        }

        // Voie Physique de l'adresse physique
        if (form.getVoieAdressePhysique() != null) {
            String voiePhysique = form.getVoieAdressePhysique();
            if (voiePhysique.length() <= 50 && voiePhysique.length() >= 5) {
                entiteEnvoye.setVoiePhysique(voiePhysique);
            } else {
                msg = "La voie Physique doit contenir 5 caractères minimun et 50 maximun.";
                throw new Exception(msg);
            }
        } else {
            msg = "La voie Physique est obligatoire.";
            throw new Exception(msg);
        }

        //Code Postale Physique de l'adresse physique
        if (form.getCpAdressePhysique() != null) {
            String cpPhysique = form.getCpAdressePhysique();
            if (cpPhysique.length() == 5) {
                entiteEnvoye.setCpPhysique(cpPhysique);
            } else {
                msg = "Le code postal Physique doit contenir 5 caractères";
                throw new Exception(msg);
            }
        } else {
            msg = "Le code postal est obligatoire.";
            throw new Exception(msg);
        }

        //voie postale de l'adresse postale
        if (form.getVoieAdressePostal() != null) {
            if ( form.getVoieAdressePostal().length() <= 50 && form.getVoieAdressePostal().length() >= 5 ) {
                entiteEnvoye.setVoiePostale(form.getVoieAdressePostal());
            } else {
                msg = "La voie Postale doit contenir 5 caractères minimun et 50 maximun";
                throw new Exception(msg);
            }
        } else {
            entiteEnvoye.setVoiePostale(null);
        }

        //code postale de l'adresse postale
        if (form.getCpAdressePostal() != null) {
            if (form.getCpAdressePostal().length() == 5) {
                entiteEnvoye.setCpPostale(form.getCpAdressePostal());
            } else {
                msg = "Le code postal de l'adresse postale doit contenir 5 caractères";
            }
        } else {
            entiteEnvoye.setCpPostale(null);
        }

        //Metier
        if (form.getMetier() != 0) {
            Metier metier = metierRepository.findById(form.getMetier()).get();
            entiteEnvoye.setMetier(metier);
        } else {
            msg= "Le métier est obligatoire";
            throw new Exception(msg);
        }

        //Site
        if ( form.getSite() != 0) {
            Site site = siteRepository.findById(form.getSite()).get();
            entiteEnvoye.setSite(site);
        }

        //Ville Physique
        if (form.getVillePhysique() != 0) {
            Ville villePhysique = villeRepository.findById(form.getVillePhysique()).get();
            entiteEnvoye.setVillePhysique(villePhysique);
        } else {
            msg= "La ville de l'adresse physique est obligatoire";
            throw new Exception(msg);
        }

        //Ville Postale
        if (form.getVillePostal() != 0) {
            Ville villePostal = villeRepository.findById(form.getVillePostal()).get();
            entiteEnvoye.setVillePostale(villePostal);
        } else {
            entiteEnvoye.setVillePostale(null);
        }

        //Entite Mere
        if (form.getEntiteMere() != null) {
           if (isCodeEntiteValideActive(form.getEntiteMere())) {
              Entite entiteMere = entiteRepository.findById(form.getEntiteMere()).get();
               entiteEnvoye.setEntiteMere(entiteMere);
           }
        } else {
            msg = "l'entité mère est obligatoire";
            throw new Exception(msg);
        }

        //Activité
        List<Integer> activites = form.getActivite();
        if (activites != null) {
            List<Activite> activiteList = activiteRepository.findAllById(activites);
            entiteEnvoye.setActivites(activiteList);
        } else {
            entiteEnvoye.setActivites(null);
        }


        //Historique
        Historique historique = genereUnHistorique(entiteEnvoye, "C");
        Collection<Historique> historiqueCollection = new ArrayList<>();
        historiqueCollection.add(historique);

         entiteEnvoye.setHistoriques(historiqueCollection);

       return entiteRepository.saveAndFlush(entiteEnvoye); //TODO Try catch

    }

    public Historique genereUnHistorique (Entite entite ,String operation) {  //TODO mettre dans une class Historique serice
        Historique historique = new Historique();

        LocalDateTime aujourdhui =  LocalDateTime.now();
        String log = "CJG972";
        String prenom = "Florian";
        String nom = "Dupont";

        historique.setCodeEntite(entite);
        historique.setDate(aujourdhui);
        historique.setOperation(operation);
        historique.setLogin(log);
        historique.setPrenom(prenom);
        historique.setNom(nom);

        return historique;
    }

}
