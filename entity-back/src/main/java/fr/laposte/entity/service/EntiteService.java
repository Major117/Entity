package fr.laposte.entity.service;

import fr.laposte.entity.model.*;
import fr.laposte.entity.repository.*;
import fr.laposte.entity.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
        System.out.println(lastCode);

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

    public Entite entiteValide(CreationForm formControle) throws Exception {

        Entite entiteValide = new Entite();

        String msg = "";

        String regex = "[a-zA-Z0-9_.()&@!?%:*€'àâäæçéèêëîïôœùûü -]*";
        Pattern pattern = Pattern.compile(regex);


        // Rh
        entiteValide.setRh(formControle.isRh());
        //Comptable
        entiteValide.setComptable(formControle.isComptable());

        // Libelle
        if (formControle.getLibelle() != null ) {
            String libelle = formControle.getLibelle();
            Matcher matcher = pattern.matcher(libelle);
            if (libelle.length() <= 50 && libelle.length() >= 3) {
                if (matcher.matches()) {
                    entiteValide.setLibelle(libelle);
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
        if (formControle.getVoieAdressePhysique() != null) {
            String voiePhysique = formControle.getVoieAdressePhysique();
            if (voiePhysique.length() <= 50 && voiePhysique.length() >= 5) {
                entiteValide.setVoiePhysique(voiePhysique);
            } else {
                msg = "La voie Physique doit contenir 5 caractères minimun et 50 maximun.";
                throw new Exception(msg);
            }
        } else {
            msg = "La voie Physique est obligatoire.";
            throw new Exception(msg);
        }

        //Code Postale Physique de l'adresse physique
        if (formControle.getCpAdressePhysique() != null && !formControle.getCpAdressePhysique().isEmpty()) {
            String cpPhysique = formControle.getCpAdressePhysique();
            if (cpPhysique.length() == 5) {
                entiteValide.setCpPhysique(cpPhysique);
            } else {
                msg = "Le code postal Physique doit contenir 5 caractères";
                throw new Exception(msg);
            }
        } else {
            msg = "Le code postal est obligatoire.";
            throw new Exception(msg);
        }

        //voie postale de l'adresse postale
        if (formControle.getVoieAdressePostal() != null && !formControle.getVoieAdressePostal().isEmpty()) {
            if ( formControle.getVoieAdressePostal().length() <= 50 && formControle.getVoieAdressePostal().length() >= 5 ) {
                entiteValide.setVoiePostale(formControle.getVoieAdressePostal());
            } else {
                msg = "La voie Postale doit contenir 5 caractères minimun et 50 maximun";
                throw new Exception(msg);
            }
        } else {
            entiteValide.setVoiePostale(null);
        }

        //code postale de l'adresse postale
        if (formControle.getCpAdressePostal() != null) {
            if (formControle.getCpAdressePostal().length() == 5) {
                entiteValide.setCpPostale(formControle.getCpAdressePostal());
            } else {
                msg = "Le code postal de l'adresse postale doit contenir 5 caractères";
            }
        } else {
            entiteValide.setCpPostale(null);
        }

        //Metier
        if (formControle.getMetier() != 0) {
            Metier metier = metierRepository.findById(formControle.getMetier()).get();
            entiteValide.setMetier(metier);
        } else {
            msg= "Le métier est obligatoire";
            throw new Exception(msg);
        }

        //Site
        if ( formControle.getSite() != 0) {
            Site site = siteRepository.findById(formControle.getSite()).get();
            entiteValide.setSite(site);
        }

        //Ville Physique
        if (formControle.getVillePhysique() != 0) {
            Ville villePhysique = villeRepository.findById(formControle.getVillePhysique()).get();
            entiteValide.setVillePhysique(villePhysique);
        } else {
            msg= "La ville de l'adresse physique est obligatoire";
            throw new Exception(msg);
        }

        //Ville Postale
        if (formControle.getVillePostal() != 0) {
            Ville villePostal = villeRepository.findById(formControle.getVillePostal()).get();
            entiteValide.setVillePostale(villePostal);
        } else {
            entiteValide.setVillePostale(null);
        }

        //Entite Mere
        if (formControle.getEntiteMere() != null) {
            if (isCodeEntiteValideActive(formControle.getEntiteMere())) {
                Entite entiteMere = entiteRepository.findById(formControle.getEntiteMere()).get();
                entiteValide.setEntiteMere(entiteMere);
            }
        } else {
            msg = "l'entité mère est obligatoire";
            throw new Exception(msg);
        }

        //Activité
        List<Integer> activites = formControle.getActivite();
        if (activites != null) {
            List<Activite> activiteList = activiteRepository.findAllById(activites);
            entiteValide.setActivites(activiteList);
        } else {
            entiteValide.setActivites(null);
        }

        return entiteValide;
    }


    public Entite nouvelleEntite (CreationForm form) throws Exception {

        Entite entiteEnvoye = entiteValide(form);

        String msg = "";
        String lastCode = entiteRepository.findLastCodeEntite();
        String newCode ;

        // Code Entite
        if (!lastCode.equals("ZZ9999")) {
            newCode = genereUnCodeEntiteUnique(lastCode);
            entiteEnvoye.setCodeEntite(newCode);
        } else {
            msg = "plus de code disponible !";
            throw new Exception(msg);
        }

        //Historique
        Historique historique = genereUnHistorique(entiteEnvoye, "C");
        Collection<Historique> historiqueCollection = new ArrayList<>();
        historiqueCollection.add(historique);

        entiteEnvoye.setHistoriques(historiqueCollection);

       return entiteRepository.saveAndFlush(entiteEnvoye);
    }


    public Entite udpdateEntite (String code, CreationForm form) throws Exception {


        Entite entiteModifie = entiteRepository.findById(code).get();

        Entite entiteEnvoye = entiteValide(form);
        entiteEnvoye.setCodeEntite(entiteModifie.getCodeEntite());

        String msg = "";
        System.out.println(entiteModifie.toString());
        System.out.println(entiteEnvoye.toString());

       if (entiteModifie.toString().equals(entiteEnvoye.toString())) {
            msg = "Modification impossible, aucun changement n'a été effectué";
            throw new Exception(msg);
        }

        Historique historique = genereUnHistorique(entiteEnvoye, "M");
        Collection<Historique> historiqueCollection = entiteModifie.getHistoriques();
        historiqueCollection.add(historique);
        entiteEnvoye.setHistoriques(historiqueCollection);

        return entiteRepository.save(entiteEnvoye);
    }

    public void deleteEntite (String code) throws Exception {

        String msg = "";
        List<String> entiteList = entiteRepository.findEntitesFilles(code);
        String msgListEntite = String.valueOf(entiteList).substring(1, entiteList.toString().length() - 1) + " .";

       if (entiteList.isEmpty()) {
           historiqueRepository.removeHistoriques(code);
           entiteRepository.removeEntiteByCodeEntite(code);
       } else {
           msg = "Vous ne pouvez pas supprimer cette Entité, car elle gère(s) : " + msgListEntite;
           throw new Exception(msg);
       }

    }

    public Historique genereUnHistorique (Entite entite ,String operation) {
        Historique historique = new Historique();

        LocalDateTime aujourdhui =  LocalDateTime.now();
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String log = user.getLog();
        String prenom = user.getPrenom();
        String nom = user.getNom();

        historique.setCodeEntite(entite);
        historique.setDate(aujourdhui);
        historique.setOperation(operation);
        historique.setLogin(log);
        historique.setPrenom(prenom);
        historique.setNom(nom);

        return historique;
    }

}
