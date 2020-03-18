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

    @Column ( name = "OPERATION")
    private String operation;

    @Column ( name = "LOG")
    private String login;

    @Column ( name = "PRENOM")
    private String prenom;

    @Column ( name = "NOM")
    private String nom;

    @ManyToOne
    @JoinColumn ( name = "CODE_ENTITE")
    private Entite entite;


}
