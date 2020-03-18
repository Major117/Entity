#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: METIER
#------------------------------------------------------------

CREATE TABLE FE_METIER
(
        CODE_METIER Int  Auto_increment  NOT NULL ,
        NOM_METIER  Varchar (50) NOT NULL
	,CONSTRAINT METIER_PK PRIMARY KEY (CODE_METIER)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: VILLE
#------------------------------------------------------------

CREATE TABLE FE_VILLE
(
        ID_VILLE  Int  Auto_increment  NOT NULL ,
        NOM_VILLE Varchar (50) NOT NULL
	,CONSTRAINT VILLE_PK PRIMARY KEY (ID_VILLE)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SITE
#------------------------------------------------------------

CREATE TABLE FE_SITE
(
        ID_SITE   Int  Auto_increment  NOT NULL ,
        CODE_SITE Int NOT NULL ,
        NOM_SITE  Varchar (50) NOT NULL ,
        ID_VILLE  Int NOT NULL
	,CONSTRAINT SITE_PK PRIMARY KEY (ID_SITE)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ACTIVITE
#------------------------------------------------------------

CREATE TABLE FE_ACTIVITE
(
        ID_ACTIVITE Int  Auto_increment  NOT NULL ,
        NOM_ACTIVITE  Varchar (50) NOT NULL
	,CONSTRAINT ACTIVITE_PK PRIMARY KEY (ID_ACTIVITE)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ENTITE
#------------------------------------------------------------

CREATE TABLE FE_ENTITE
(
        CODE_ENTITE                     Char (6) NOT NULL ,
        LIBELLE                         Varchar (250) NOT NULL ,
        RH                              Bool NOT NULL ,
        COMPTABLE                       Bool NOT NULL ,
        VOIE_PHYSIQUE                   Varchar (50) NOT NULL ,
        CP_PHYSIQUE                     Char (5) NOT NULL ,
        VOIE_POSTALE                    Varchar (50) NOT NULL ,
        CP_POSTALE                      Char (5) NOT NULL ,
        ID_SITE                         Int ,
        CODE_METIER                     Int NOT NULL ,
        CODE_ENTITE_MERE                Char (6) NOT NULL ,
        ID_VILLE_PHYSIQUE               Int NOT NULL ,
        ID_VILLE_POSTALE                Int
	,CONSTRAINT ENTITE_PK PRIMARY KEY (CODE_ENTITE)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: HISTORIQUE
#------------------------------------------------------------

CREATE TABLE FT_HISTORIQUE(
        ID_HISTORIQUE Int  Auto_increment  NOT NULL ,
        DATE          Datetime NOT NULL ,
        OPERATION     Char (1) NOT NULL ,
        LOG           Varchar (50) NOT NULL ,
        PRENOM        Varchar (50) NOT NULL ,
        NOM           Varchar (50) NOT NULL ,
        CODE_ENTITE   Char (6) NOT NULL
	,CONSTRAINT HISTORIQUE_PK PRIMARY KEY (ID_HISTORIQUE)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ENTITE_ACTIVITE
#------------------------------------------------------------

CREATE TABLE FR_ENTITE_ACTIVITE(
        ID_ACTIVITE Int NOT NULL ,
        CODE_ENTITE   Char (6) NOT NULL
	,CONSTRAINT Concernee_PK PRIMARY KEY (ID_ACTIVITE,CODE_ENTITE)
)ENGINE=InnoDB;




ALTER TABLE FE_SITE
	ADD CONSTRAINT SITE_VILLE_FK
	FOREIGN KEY (ID_VILLE)
	REFERENCES FE_VILLE(ID_VILLE);

ALTER TABLE FE_ENTITE
	ADD CONSTRAINT ENTITE_SITE_FK
	FOREIGN KEY (ID_SITE)
	REFERENCES FE_SITE (ID_SITE);

ALTER TABLE FE_ENTITE
	ADD CONSTRAINT ENTITE_METIER_FK
	FOREIGN KEY (CODE_METIER)
	REFERENCES FE_METIER (CODE_METIER);

ALTER TABLE FE_ENTITE
	ADD CONSTRAINT ENTITE_ENTITE_MERE_FK
	FOREIGN KEY (CODE_ENTITE_MERE)
	REFERENCES FE_ENTITE (CODE_ENTITE);

ALTER TABLE FE_ENTITE
	ADD CONSTRAINT ENTITE_VILLE_PHYSIQUE_FK
	FOREIGN KEY (ID_VILLE_PHYSIQUE)
	REFERENCES FE_VILLE (ID_VILLE);

ALTER TABLE FE_ENTITE
	ADD CONSTRAINT ENTITE_VILLE_POSTALE_FK
	FOREIGN KEY (ID_VILLE_POSTALE)
	REFERENCES FE_VILLE (ID_VILLE);

ALTER TABLE FT_HISTORIQUE
	ADD CONSTRAINT HISTORIQUE_ENTITE_FK
	FOREIGN KEY (CODE_ENTITE)
	REFERENCES FE_ENTITE(CODE_ENTITE);

ALTER TABLE FR_ENTITE_ACTIVITE
	ADD CONSTRAINT CONCERNEE_ACTIVITE_FK
	FOREIGN KEY (ID_ACTIVITE)
	REFERENCES FE_ACTIVITE (ID_ACTIVITE);

ALTER TABLE FR_ENTITE_ACTIVITE
	ADD CONSTRAINT CONCERNEE_ENTITE_FK
	FOREIGN KEY (CODE_ENTITE)
	REFERENCES FE_ENTITE (CODE_ENTITE);