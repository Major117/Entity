package com.entity.model;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table (name = "FE_ENTITE",
        schema = "entity")
public class Entite {



    @Id
    @Column ( name = "CODE_ENTITE")
    private String codeEntite;

    @Column (name = "LIBELLE")
    private String libelle;

    @Column (name = "RH")
    private boolean rh;

    @Column (name = "COMPTABLE")
    private boolean comptable;

    @Column (name = "VOIE_PHYSIQUE")
    private String voiePhysique;

    @Column (name = "CP_PHYSIQUE")
    private String cpPhysique;

    @Column (name = "VOIE_POSTALE")
    private String voiePostale;

    @Column (name = "CP_POSTALE")
    private String cpPostale;

    @OneToOne
    @JoinColumn( name="CODE_METIER")
    private Metier metier;

    @OneToOne
    @JoinColumn( name="ID_SITE")
    private Site site;

    @OneToOne
    @JoinColumn (name = "ID_VILLE_PHYSIQUE")
    private Ville villePhysique;

    @OneToOne
    @JoinColumn (name = "ID_VILLE_POSTALE")
    private Ville villePostale;

    @OneToOne
    @MapsId //(fetch = FetchType.LAZY , mappedBy = "CODE_ENTITE_MERE" )
    private Entite entiteMere;

    @ManyToMany
    @JoinTable(
            name = "FR_ENTITE_ACTIVITE",
            joinColumns = { @JoinColumn(name = "ID_ACTIVITE") },
            inverseJoinColumns = { @JoinColumn(name = "CODE_ENTITE") })
    private Collection<Activite> activite;




 }
