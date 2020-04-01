package fr.laposte.entity.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@Table (name = "FE_ENTITE")
public class Entite {

 public Entite(String codeEntite, String libelle, boolean rh, boolean comptable, String voiePhysique, String cpPhysique, String voiePostale, String cpPostale, Metier metier, Site site, Ville villePhysique, Ville villePostale, Entite entiteMere, List<Activite> activites,List<Historique> historiques) {
       this.codeEntite = codeEntite;
       this.libelle = libelle;
       this.rh = rh;
       this.comptable = comptable;
       this.voiePhysique = voiePhysique;
       this.cpPhysique = cpPhysique;
       this.voiePostale = voiePostale;
       this.cpPostale = cpPostale;
       this.metier = metier;
       this.site = site;
       this.villePhysique = villePhysique;
       this.villePostale = villePostale;
       this.entiteMere = entiteMere;
       this.activites = activites;
       this.historiques = historiques;
 }

    public Entite() {

    }

    @Id
    @Column ( name = "CODE_ENTITE", length = 6, nullable = false, columnDefinition = "CHAR(6)")
    private String codeEntite;

   @NotNull
    @Column (name = "LIBELLE")
    private String libelle;

   @NotNull
    @Column (name = "RH")
    private boolean rh;

    @NotNull
    @Column (name = "COMPTABLE")
    private boolean comptable;

    @NotNull
    @Column(name = "VOIE_PHYSIQUE", length = 50)
    private String voiePhysique;

    @NotNull
    @Column (name = "CP_PHYSIQUE", length = 5)
    private String cpPhysique;

    @Column (name = "VOIE_POSTALE", length = 50)
    private String voiePostale;

    @Column (name = "CP_POSTALE", length = 5)
    private String cpPostale;

    @OneToOne
    @NotNull
    @JoinColumn( name="CODE_METIER")
    private Metier metier;

    @OneToOne
    @JoinColumn( name="ID_SITE")
    private Site site;

    @OneToOne
    @NotNull
    @JoinColumn (name = "ID_VILLE_PHYSIQUE")
    private Ville villePhysique;

    @OneToOne
    @JoinColumn (name = "ID_VILLE_POSTALE")
    private Ville villePostale;

    @OneToOne (fetch = FetchType.LAZY )
    @JoinColumn (name = "CODE_ENTITE_MERE")
    private Entite entiteMere;

    @ManyToMany ( fetch = FetchType.EAGER)
    @Fetch( FetchMode.SUBSELECT)
    @JoinTable(
            name = "FR_ENTITE_ACTIVITE",
            inverseJoinColumns =  @JoinColumn(name = "ID_ACTIVITE") ,
            joinColumns =  @JoinColumn(name = "CODE_ENTITE"))
    private Collection<Activite> activites;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
    @Fetch( FetchMode.SUBSELECT)
    @JoinColumn(name = "CODE_ENTITE")
    private Collection<Historique> historiques;


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

    public String getVoiePhysique() {
        return voiePhysique;
    }

    public void setVoiePhysique(String voiePhysique) {
        this.voiePhysique = voiePhysique;
    }

    public String getCpPhysique() {
        return cpPhysique;
    }

    public void setCpPhysique(String cpPhysique) {
        this.cpPhysique = cpPhysique;
    }

    public String getVoiePostale() {
        return voiePostale;
    }

    public void setVoiePostale(String voiePostale) {
        this.voiePostale = voiePostale;
    }

    public String getCpPostale() {
        return cpPostale;
    }

    public void setCpPostale(String cpPostale) {
        this.cpPostale = cpPostale;
    }

    public Metier getMetier() {
        return metier;
    }

    public void setMetier(Metier metier) {
        this.metier = metier;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Ville getVillePhysique() {
        return villePhysique;
    }

    public void setVillePhysique(Ville villePhysique) {
        this.villePhysique = villePhysique;
    }

    public Ville getVillePostale() {
        return villePostale;
    }

    public void setVillePostale(Ville villePostale) {
        this.villePostale = villePostale;
    }

    public Entite getEntiteMere() {
        return entiteMere;
    }

    public void setEntiteMere(Entite entiteMere) {
        this.entiteMere = entiteMere;
    }

    public Collection<Activite> getActivites() {
        return activites;
    }

    public void setActivites(Collection<Activite> activites) {
        this.activites = activites;
    }

    public Collection<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(Collection<Historique> historiques) {
        this.historiques = historiques;
    }

    @Override
    public String toString() {
        return "Entite{" +
                "codeEntite='" + codeEntite + '\'' +
                ", libelle='" + libelle + '\'' +
                ", rh=" + rh +
                ", comptable=" + comptable +
                ", voiePhysique='" + voiePhysique + '\'' +
                ", cpPhysique='" + cpPhysique + '\'' +
                ", voiePostale='" + voiePostale + '\'' +
                ", cpPostale='" + cpPostale + '\'' +
                ", metier=" + metier +
                ", site=" + site +
                ", villePhysique=" + villePhysique +
                ", villePostale=" + villePostale +
                ", entiteMere=" + entiteMere +
                ", activites=" + activites +
                ", historiques=" + historiques +
                '}';
    }
}

