
export class CreationForm {

  libelle: string;
  entiteMere: string;
  metier: number;
  voieAdressePhysique: string;
  cpAdressePhysique: string;
  villePhysique: number;
  voieAdressePostal: string;
  cpAdressePostal: string;
  villePostal: number;
  site: number;
  rh: boolean;
  comptable: boolean;
  activite: number[];


  constructor(libelle: string, entiteMere: string, metier: number, voieAdressePhysique: string, cpAdressePhysique: string, villePhysique: number, voieAdressePastal: string, cpAdressePostal: string, villePostal: number, site: number, rh: boolean, comptable: boolean, activite: number[]) {
    this.libelle = libelle;
    this.entiteMere = entiteMere;
    this.metier = metier;
    this.voieAdressePhysique = voieAdressePhysique;
    this.cpAdressePhysique = cpAdressePhysique;
    this.villePhysique = villePhysique;
    this.voieAdressePostal = voieAdressePastal;
    this.cpAdressePostal = cpAdressePostal;
    this.villePostal = villePostal;
    this.site = site;
    this.rh = rh;
    this.comptable = comptable;
    this.activite = activite;
  }
}
