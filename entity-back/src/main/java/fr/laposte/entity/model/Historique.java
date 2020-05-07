package fr.laposte.entity.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table (name = "FT_HISTORIQUE")
public class Historique {

    public Historique() {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "ID_HISTORIQUE")
    private int idHistorique;

    @Column ( name = "DATE")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date;

    @Column ( name = "OPERATION", length = 1)
    private String operation;

    @Column ( name = "LOG", length = 50)
    private String login;

    @Column ( name = "PRENOM", length = 50)
    private String prenom;

    @Column ( name = "NOM", length = 50)
    private String nom;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "CODE_ENTITE")
    @JsonIgnore
    private Entite codeEntite;


    public int getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Entite getCodeEntite() {
        return codeEntite;
    }

    public void setCodeEntite(Entite codeEntite) {
        this.codeEntite = codeEntite;
    }

    @Override
    public String toString() {
        return "Historique{" +
                "idHistorique=" + idHistorique +
                ", date=" + date +
                ", operation='" + operation + '\'' +
                ", login='" + login + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
