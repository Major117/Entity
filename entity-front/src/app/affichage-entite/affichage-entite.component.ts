import {Component, OnInit} from '@angular/core';
import {Entite} from "../models/Entite";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {RechercheService} from "../services/recherche.service";
import {switchMap} from "rxjs/operators";
import {TokenStorageService} from "../services/token-storage.service";

@Component({
  selector: 'app-affichage-entite',
  templateUrl: './affichage-entite.component.html',
  styleUrls: ['./affichage-entite.component.css']
})
export class AffichageEntiteComponent implements OnInit {

  entite: Entite;
  active: boolean;

  entiteTechnique = 'AA0000';
  desactivation = 'D';
  reactivation = 'R';
  modification = 'M';
  msgDeModification = 'Aucune modification n’a été apportée ';


  constructor(private route: ActivatedRoute,
              private router: Router,
              private rs: RechercheService,
              public auth: TokenStorageService) {
  }

  ngOnInit(): void {

    this.route.paramMap.pipe(switchMap((params: ParamMap) =>
      this.rs.rechercheAvecCodeEntite(params.get('codeEntite'))))
      .subscribe((data: Entite) => {
        this.entite = data;
      }, error => {
        this.router.navigateByUrl(`/accueil`);
      });

  }

  initDateHistorique(crt: string, msg?: string) {

    let historiqueList = this.entite.historiques.filter(historique => historique.operation === crt);
    this.active = null;

    if (historiqueList.length === 0) {
      if (msg) {
        return msg
      } else {
        return false
      }
    } else if (crt === this.desactivation || crt === this.reactivation) {
      let historiqueListActive = this.entite.historiques.filter
      (historique => historique.operation === this.desactivation || historique.operation === this.reactivation);
      let operationFinale = historiqueListActive[historiqueListActive.length - 1].operation;
      if (operationFinale === this.desactivation) {
        this.active = false;
        return historiqueListActive[historiqueListActive.length - 1].date;
      } else if (operationFinale === this.reactivation) {
        this.active = true;
        return historiqueListActive[historiqueListActive.length - 1].date;
      }
    } else if (historiqueList.length === 1) {
      return historiqueList[0].date;
    } else {
      return historiqueList[historiqueList.length - 1].date;
    }
  }

  historiqueGestionnaire(op: string) {

    switch(op) {
      case 'C' :
        return 'Création';
      case 'D' :
        return 'Désactivation';
      case  'M' :
        return 'Modification';
      case 'R' :
        return 'Réactivation';

    }
  }

  verifieSite() {
    if (!this.entite.site) {
      return 'Aucun';
    }
  }

  initBoolean(bool: boolean) {
    if (bool === true) {
      return 'oui';
    } else {
      return 'non';
    }

  }

  ouvreEntiteMere(code) {
    this.router.navigateByUrl(`/entite/${code}`);
  }

  ouvreLaModification(code) {
    this.router.navigate(['/modification'], {queryParams: {code: code}})
  }

  isAdmin() {
    if (this.auth.isLoggedIn()) {
      return this.auth.getUser().roles == 'ROLE_Admin';
    } else {
      return false
    }
  }

}
