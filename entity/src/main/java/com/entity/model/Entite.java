package com.entity.model;


import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
@Table (name = "FE_ENTITE")
public class Entite {

 public Entite(String codeEntite, String libelle, boolean rh, boolean comptable, String voiePhysique, String cpPhysique, String voiePostale, String cpPostale, Metier metier, Site site, Ville villePhysique, Ville villePostale, Entite entiteMere, List<Activite> activite) {
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
       this.activite = activite;
 }

 @Id
    @NotNull
    @Column ( name = "CODE_ENTITE", length = 6)
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
    @NotNull
    @JoinColumn (name = "CODE_ENTITE_MERE")
    private Entite entiteMere;

    @ManyToMany// ( fetch = FetchType.EAGER)
    @JoinTable(
            name = "FR_ENTITE_ACTIVITE",
            joinColumns = { @JoinColumn(name = "ID_ACTIVITE") },
            inverseJoinColumns = { @JoinColumn(name = "CODE_ENTITE") })
    private Collection<Activite> activite;


}

