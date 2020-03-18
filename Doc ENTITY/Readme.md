# ENTITY 

 ENTITY est une nouvelle application qui s'inspire d'une application existante dans le SI La Poste (REGATE), elle permet de gérer le référentiel des entités.

Une Entité représente un lieu d'exercice des activités du Groupe La Poste, c'est un référentiel qui est utilisé par plusieurs applications qui exploitent ses données.

Une Entité : 
   - est définie par un code unique
   - possède des propiétés (métier, libellé, adresse, ...)
   - s'intègre dans une hiérarchie 

L'ensemble des entités et de leurs informations est en accès libre.

La gestion des entités est réservée aux profils spécifiques :

   1. Un gestionnaire,qui peut créer, modifier, désactiver ou réactiver les entités

   2. Un administrateur qui en plus des droits gestionnaire peut supprimer une entité et gérer les données de référence (métiers, sites, ...)



## Technologies et outils utilisés:

#### Technologies :

Base de données : MySQL.

Backend : Java - Maven - SpringBoot -  Hibernate.

Frontend : Angular - html - css.

#### Outils :

Les IDE : DataGrip pour le Script SQL ; IntelliJ pour JAVA  et Angular. 

jMerise pour le MCD e MLD.

StarUML pour les USE CASE.

Postman pour tester les endpoints.

Git et Git hub pour héberger les repositories.

Adobe xD qui est un logiciel pour maquetter.

## User Stories :

US-01<br> **En tant qu**’utilisateur, <br>**je veux** pouvoir consulter une entité <br>**afin d**'obtenir ses propriétés.

US-02<br> **En tant qu**’utilisateur,<br> **je veux** que l’interface soit uniforme<br> **afin d**’utiliser simplement le référentiel.

US-03<br> **En tant qu**'utilisateur,<br> **je veux** pouvoir rechercher une ou plusieurs entités <br>**afin de** consulter leurs propriétés.

US-04<br> **En tant qu**’utilisateur,<br> **je veux** pouvoir exporter les propriétés d’une entité au format Excel<br> **afin de** travailler ces données dans l’outil Excel.

US-05<br> **En tant que** gestionnaire,<br> **je veux** pouvoir créer une entité<br> **afin de** l’intégrer dans le référentiel.

US-06<br> **En tant que** gestionnaire,<br> **je veux** pouvoir modifier une entité<br> **afin de** mettre à jour ses propriétés.

US-07<br> **En tant que** gestionnaire,<br> **je veux** pouvoir désactiver une entité<br> **afin de** ne plus pouvoir modifier ses propriétés et l’utiliser comme entité de rattachement.

US-08<br> **En tant que** gestionnaire,<br> **je veux** pourvoir réactiver une entité<br> **afin de** pouvoir la modifier et l’utiliser dans le référentiel.

US-09<br> **En tant qu**’administrateur,<br> **je veux** pouvoir supprimer une entité<br> **afin de** ne plus la gérer dans le référentiel.

US-10<br> **En tant qu**’administrateur,<br> **je veux** pouvoir gérer les données de référence du référentiel<br> **afin d**’assurer la qualité des données des entités.






