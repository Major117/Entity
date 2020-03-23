package com.entity.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table ( name = "FE_METIER")
public class Metier {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column( name = "CODE_METIER")
    private int codeMetier;

    @NotNull
    @Column ( name = "NOM_METIER", length = 50)
    private String nomMetier;

}
