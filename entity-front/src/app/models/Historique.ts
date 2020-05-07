import {Entite} from "./Entite";

export interface Historique {

  idHistorique : number
  date: Date
  operation: string;
  login: string
  prenom: string;
  nom: string;
  codeEntite : Entite;

}
