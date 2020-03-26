package com.entity.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table ( name = "FE_SITE")
public class Site {

    public Site() {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column ( name = "ID_SITE")
    private int idSite;

    @NotNull
    @Column ( name = "CODE_SITE")
    private int codeSite;

    @NotNull
    @Column ( name = "NOM_SITE", length = 50)
    private String nomSite;

    @NotNull
    @OneToOne (fetch = FetchType.EAGER )
    @JoinColumn ( name = "ID_VILLE")
    private Ville ville;

    public int getIdSite() {
        return idSite;
    }

    public void setIdSite(int idSite) {
        this.idSite = idSite;
    }

    public int getCodeSite() {
        return codeSite;
    }

    public void setCodeSite(int codeSite) {
        this.codeSite = codeSite;
    }

    public String getNomSite() {
        return nomSite;
    }

    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Site{" +
                "idSite=" + idSite +
                ", codeSite=" + codeSite +
                ", nomSite='" + nomSite + '\'' +
                ", ville=" + ville +
                '}';
    }
}
