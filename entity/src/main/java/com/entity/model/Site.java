package com.entity.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table ( name = "FE_SITE")
public class Site {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "ID_SITE")
    private int idSite;

    @Column ( name = "CODE_SITE")
    private int codeSite;

    @Column ( name = "NOM_SITE")
    private String nomSite;

    @OneToOne
    @JoinColumn ( name = "ID_VILLE")
    private Ville ville;
}
