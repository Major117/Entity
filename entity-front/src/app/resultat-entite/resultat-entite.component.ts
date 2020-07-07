import {Component, Input, OnInit} from '@angular/core';
import {Entite} from "../models/Entite";
import {RechercheService} from "../services/recherche.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-resultat-entite',
  templateUrl: './resultat-entite.component.html',
  styleUrls: ['./resultat-entite.component.css']
})
export class ResultatEntiteComponent implements OnInit {

  constructor( private rs : RechercheService, private router: Router) {
  }

  @Input() listEntite: Entite[];

  displayedColumns: string[] = ['Code', 'Libellé', 'Métier', 'Ville', 'Active', 'Date de création'];

  ngOnInit(): void {
  }

  /**
   * Vérifie si l'entité est active.
   * @param str
   */
  isActive(str: string): string {
    let active;

    if (str == 'D') {
      active = 'non';
    } else {
      active = 'oui';
    }
    return active;
  }

  /**
   * Redirige sur la consultation d'entité après avoir cliqué.
   * @param code
   */
  choixEntite(code) {
      this.router.navigateByUrl(`/entite/${code}`);
  }


}
