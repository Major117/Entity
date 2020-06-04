package fr.laposte.entity.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.laposte.entity.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class UserDetailsImpl implements UserDetails {

    private String log;

    private String prenom;

    private String nom;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String log, String prenom, String nom, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.log = log;
        this.prenom = prenom;
        this.nom = nom;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));

        return new UserDetailsImpl(
                user.getLog(),
                user.getPrenom(),
                user.getNom(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public String getLog() {
        return log;
    }

    public String getPrenom() {
        return prenom;
    }


    public String getNom() {
        return nom;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(log, user.log);
    }

}
