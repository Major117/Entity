package fr.laposte.entity.model;

import javax.persistence.*;

@Entity
@Table ( name = "fe_user")
public class User  {

    @Id
    @Column( name = "log")
    private String log;

    @Column (name = "prenom")
    private String prenom;

    @Column (name = "nom")
    private String nom;

    @Column (name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column (name = "role")
    private Role role;

    public User(String log, String prenom, String nom, String password, Role role) {
        this.log = log;
        this.prenom = prenom;
        this.nom = nom;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
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

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
