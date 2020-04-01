package fr.laposte.entity.model;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table (name = "FT_HISTORIQUE")
public class Historique {

    public Historique() {
    }

    @Id
    @Column ( name = "ID_HISTORIQUE")
    private int idHistorique;

    @Column ( name = "DATE")
    private LocalDateTime date;

    @Column ( name = "OPERATION", length = 1)
    private String operation;

    @Column ( name = "LOG", length = 50)
    private String login;

    @Column ( name = "PRENOM", length = 50)
    private String prenom;

    @Column ( name = "NOM", length = 50)
    private String nom;


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
