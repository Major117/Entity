package com.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table ( name = "FE_VILLE")
public class Ville {

    public Ville() {
    }

    @Id
    @NotNull
    @Column ( name = "ID_VILLE")
    private int idVille;

    @NotNull
    @Column ( name = "NOM_VILLE", length = 50)
    private String nomVille;

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "idVille=" + idVille +
                ", nomVille='" + nomVille + '\'' +
                '}';
    }
}
