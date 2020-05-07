package fr.laposte.entity.service;


import java.util.List;

public class CreationForm {


    String libelle;
    String entiteMere;
    int metier;
    String voieAdressePhysique;
    String cpAdressePhysique;
    int villePhysique;
    String voieAdressePostal;
    String cpAdressePostal;
    int villePostal;
    int site;
    boolean rh;
    boolean comptable;
    List<Integer> activite;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEntiteMere() {
        return entiteMere;
    }

    public void setEntiteMere(String entiteMere) {
        this.entiteMere = entiteMere;
    }

    public int getMetier() {
        return metier;
    }

    public void setMetier(int metier) {
        this.metier = metier;
    }

    public String getVoieAdressePhysique() {
        return voieAdressePhysique;
    }

    public void setVoieAdressePhysique(String voieAdressePhysique) {
        this.voieAdressePhysique = voieAdressePhysique;
    }

    public String getCpAdressePhysique() {
        return cpAdressePhysique;
    }

    public void setCpAdressePhysique(String cpAdressePhysique) {
        this.cpAdressePhysique = cpAdressePhysique;
    }

    public int getVillePhysique() {
        return villePhysique;
    }

    public void setVillePhysique(int villePhysique) {
        this.villePhysique = villePhysique;
    }

    public String getVoieAdressePostal() {
        return voieAdressePostal;
    }

    public void setVoieAdressePostal(String voieAdressePostal) {
        this.voieAdressePostal = voieAdressePostal;
    }

    public String getCpAdressePostal() {
        return cpAdressePostal;
    }

    public void setCpAdressePostal(String cpAdressePostal) {
        this.cpAdressePostal = cpAdressePostal;
    }

    public int getVillePostal() {
        return villePostal;
    }

    public void setVillePostal(int villePostal) {
        this.villePostal = villePostal;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public boolean isRh() {
        return rh;
    }

    public void setRh(boolean rh) {
        this.rh = rh;
    }

    public boolean isComptable() {
        return comptable;
    }

    public void setComptable(boolean comptable) {
        this.comptable = comptable;
    }

    public List<Integer> getActivite() {
        return activite;
    }

    public void setActivite(List<Integer> activite) {
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "CreationForm{" +
                "libelle='" + libelle + '\'' +
                ", entiteMere='" + entiteMere + '\'' +
                ", metier=" + metier +
                ", voieAdressePhysique='" + voieAdressePhysique + '\'' +
                ", cpAdressePhysique='" + cpAdressePhysique + '\'' +
                ", villePhysique=" + villePhysique +
                ", voieAdressePostal='" + voieAdressePostal + '\'' +
                ", cpAdressePostal='" + cpAdressePostal + '\'' +
                ", villePostal=" + villePostal +
                ", site=" + site +
                ", rh=" + rh +
                ", comptable=" + comptable +
                ", activite=" + activite +
                '}';
    }
}
