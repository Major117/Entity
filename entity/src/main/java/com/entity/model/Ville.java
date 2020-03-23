package com.entity.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table ( name = "FE_VILLE")
public class Ville {


    @Id
    @NotNull
    @Column ( name = "ID_VILLE")
    private int idVille;

    @NotNull
    @Column ( name = "NOM_VILLE", length = 50)
    private String nomVille;
}
