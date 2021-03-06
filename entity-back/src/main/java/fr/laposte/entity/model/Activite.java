package fr.laposte.entity.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table (name = "FE_ACTIVITE")
public class Activite {

    public Activite() {

    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column (name = "ID_ACTIVITE")
    private int idActivite;

    @NotNull
    @Column ( name = "NOM_ACTIVITE", length = 50)
    private String nomActivite;

    /*@ManyToMany
    private Collection<Entite> entite;*/

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    @Override
    public String toString() {
        return "Activite{" +
                "idActivite=" + idActivite +
                ", nomActivite='" + nomActivite + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activite activite = (Activite) o;

        if (idActivite != activite.idActivite) return false;
        return nomActivite.equals(activite.nomActivite);
    }
}
