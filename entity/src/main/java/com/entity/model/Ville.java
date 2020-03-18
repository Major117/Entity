package com.entity.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table ( name = "FE_VILLE")
public class Ville {

    @Id
    @Column ( name = "ID_VILLE")
    private int idVille;

    @Column ( name = "NOM_VILLE")
    private String nomVille;
}
