package com.entity.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table ( name = "FE_SITE")
public class Site {

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
}
