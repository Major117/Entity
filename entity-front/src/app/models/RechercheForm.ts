export class RechercheForm {

  codeEntite : string;
  libelle : string ;
  metier : number ;
  ville : number;
  active : Boolean;
  rh : Boolean;
  date : Date ;
  activite : number;


  constructor(codeEntite: string, libelle: string, metier: number, ville: number, active: Boolean, rh: Boolean, date: Date, activite: number) {
    this.codeEntite = codeEntite;
    this.libelle = libelle;
    this.metier = metier;
    this.ville = ville;
    this.active = active;
    this.rh = rh;
    this.date = date ;
    this.activite = activite;
  }

}
