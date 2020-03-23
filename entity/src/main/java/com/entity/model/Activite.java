package com.entity.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Column (name = "ID_ACTIVITE")
    private int idActivite;

    @NotNull
    @Column ( name = "NOM_ACTIVITE", length = 50)
    private String nomActivite;

    @ManyToMany
    private Collection<Entite> entite;

}
