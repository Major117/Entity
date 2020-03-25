package com.entity.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table (name = "FT_HISTORIQUE")
public class Historique {


    @Id
    @Column ( name = "ID_HISTORIQUE")
    private int idHistorique;

    @Column ( name = "DATE")
    private LocalDateTime date;

    @Column ( name = "OPERATION", length = 1)
    private String operation;

    @Column ( name = "LOG", length = 50)
    private String login;

    @Column ( name = "PRENOM", length = 50)
    private String prenom;

    @Column ( name = "NOM", length = 50)
    private String nom;

 /*   @ManyToOne
    @JoinColumn ( name = "CODE_ENTITE")
    private Entite entite;*/


}
