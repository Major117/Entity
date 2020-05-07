package fr.laposte.entity.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table ( name = "FE_METIER")
public class Metier {

    public Metier() {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column( name = "CODE_METIER")
    private int codeMetier;

    @NotNull
    @Column ( name = "NOM_METIER", length = 50)
    private String nomMetier;


    public int getCodeMetier() {
        return codeMetier;
    }

    public void setCodeMetier(int codeMetier) {
        this.codeMetier = codeMetier;
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
                "codeMetier=" + codeMetier +
                ", nomMetier='" + nomMetier + '\'' +
                '}';
    }
}
