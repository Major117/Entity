import {Metier} from "./Metier";
import {Ville} from "./Ville";
import {Activite} from "./Activite";
import {Site} from "./Site";
import {Historique} from "./Historique";

export interface Entite {

  codeEntite: string;
  libelle: string;
  rh: boolean;
  comptable: boolean;
  voiePhysique: string;
  cpPhysique: string;
  voiePostale: string;
  cpPostale: string;
  metier: Metier;
  site: Site;
  villePhysique: Ville;
  villePostale: Ville;
  entiteMere: Entite;
  activites: Activite[];
  historiques: Historique[];

}
