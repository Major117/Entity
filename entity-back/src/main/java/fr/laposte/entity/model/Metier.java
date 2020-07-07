package fr.laposte.entity.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table ( name = "FE_METIER")
public class Metier {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column( name = "ID_METIER")
    private int idMetier;

    @NotNull
    @Column ( name = "NOM_METIER", length = 50)
    private String nomMetier;

    public Metier() {
    }


    public int getIdMetier() {
        return idMetier;
    }

    public void setIdMetier(int codeMetier) {
        this.idMetier = codeMetier;
    }

    public String getNomMetier() {
        return nomMetier;
    }

    public void setNomMetier(String nomMetier) {
        this.nomMetier = nomMetier;
    }

    @Override
    public String toString() {
        return "Metier{" +
                "codeMetier=" + idMetier +
                ", nomMetier='" + nomMetier + '\'' +
                '}';
    }
}
