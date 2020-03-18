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
@Table (name = "FE_ACTIVITE")
public class Activite {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID_ACTIVITE")
    private int idActivite;

    @Column ( name = "NOM_ACTIVITE")
    private String nomActivite;

    @ManyToMany
    private Collection<Entite> entite;

}
