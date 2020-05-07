package fr.laposte.entity.service;

import java.util.Date;

public class RechercheForm {

   String codeEntite;
   String libelle ;
   int metier ;
   int ville;
   Boolean active;
   Boolean rh;
   Date date;
   int activite;

    public String getCodeEntite() {
        return codeEntite;
    }

    public void setCodeEntite(String codeEntite) {
        this.codeEntite = codeEntite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getMetier() {
        return metier;
    }

    public void setMetier(int metier) {
        this.metier = metier;
    }

    public int getVille() {
        return ville;
    }

    public void setVille(int ville) {
        this.ville = ville;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getRh() {
        return rh;
    }

    public void setRh(Boolean rh) {
        this.rh = rh;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getActivite() {
        return activite;
    }

    public void setActivite(int activite) {
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "RechercheForm{" +
                "codeEntite='" + codeEntite + '\'' +
                ", libelle='" + libelle + '\'' +
                ", metier=" + metier +
                ", ville=" + ville +
                ", active=" + active +
                ", rh=" + rh +
                ", date=" + date +
                ", activite=" + activite +
                '}';
    }
}
