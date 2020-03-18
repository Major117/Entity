package com.entity.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table ( name = "FE_METIER")
public class Metier {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "CODE_METIER")
    private int codeMetier;

    @Column ( name = "NOM_METIER")
    private String nomMetier;

}
