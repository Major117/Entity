import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {RechercheService} from "../services/recherche.service";
import {RechercheForm} from "../models/RechercheForm";
import {Metier} from "../models/Metier";
import {Ville} from "../models/Ville";
import {Activite} from "../models/Activite";
import {Router} from "@angular/router";
import {Entite} from "../models/Entite";
import {FormulaireService} from "../services/formulaire.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {KeepService} from "../services/keep.service";


@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {

  rechercheForm: FormGroup;
  entite: Entite;
  messageError: string;
  active: string;
  listEntite: Entite[];
  metier: Metier[];
  ville: Ville[];
  activite: Activite[];


  constructor(private formBuilder: FormBuilder,
              private formService: FormulaireService ,
              private rechercheService: RechercheService,
              private keepService : KeepService,
              private router: Router,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {

    this.formService.chargeLesMetiers()
      .subscribe((data: any[]) => {
          this.metier = data;
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });

   /* this.formService.chargeLesVilles()
      .subscribe((data: any[]) => {
          this.ville = data;
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });*/

    this.formService.chargeLesActivites()
      .subscribe((data: any[]) => {
          this.activite = data;
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });


    this.rechercheForm = this.formBuilder.group({
      codeEntite: '',
      libelle: '',
      metier: null,
      ville: null,
      rh: 'null',
      active: 'null',
      date: '',
      activite: null
    });

  }

  /**
   * Recherche une entité avec son code.
   * @param code
   */
  rechercheParCodeEntite(code ?: string) {
    this.entite = null;
    this.messageError = null;
    let codeDuForm = this.rechercheForm.get('codeEntite').value;

    if (codeDuForm) {
      code = codeDuForm;
    }

    this.rechercheService.rechercheAvecCodeEntite(code)
      .subscribe(data => { this.entite = data;
        this.router.navigateByUrl(`/entite/${this.entite.codeEntite}`)},
        error => {this.messageError = error.error.message;
                        this.snackBarError(this.messageError);
      });

  }

  chargeVille(lettre: string) {

  if (lettre.length > 2 ){
    this.formService.chargeLesVilles(lettre)
      .subscribe((data: any[]) => {
         this.ville = data;
       },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });
      }
    }

  /**
   * Recherche une entité avec une liste de critères.
   */
  rechercheMultiCritere() {
    this.listEntite = null;
    this.messageError = null;   //vide les infos

    const formValue = this.rechercheForm.value;

    this.rechercheService.rechercheMultiCritere(formValue)
      .subscribe((data: any[]) => {
          this.listEntite = data;
          if (this.listEntite.length == 1) {
            this.rechercheParCodeEntite(this.listEntite[0].codeEntite); //Affiche directement l'entité
          } else if (this.listEntite.length == 100) {
            this.messageError =
              "Le résultat de la recherche contient plus de 100 entités, " +
              "seules les 100 premières sont présentées." +
              " Merci d’affiner vos critères de recherche.";
            this.snackBarError(this.messageError);
          }
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        })


  }

  /**
   * Rétablis les valeurs par défaut du formulaire
   */
  reinitialiser() {
    this.rechercheForm.get('codeEntite').setValue('');
    this.rechercheForm.get('libelle').setValue('');
    this.rechercheForm.get('metier').setValue(null);
    this.rechercheForm.get('ville').setValue(null);
    this.rechercheForm.get('active').setValue('null');
    this.rechercheForm.get('rh').setValue('null');
    this.rechercheForm.get('date').setValue('');
    this.rechercheForm.get('activite').setValue(null);
  }

  /**
   * Oriente la recherche multicritère
   * 1. par le code
   * 2. par une liste de critère
   */
  envoyeFormulaire() {
    const code = this.rechercheForm.get('codeEntite').value;

    this.entite = null;
    this.listEntite = null;

    if (code) {
      this.rechercheParCodeEntite();
    } else {
      this.rechercheMultiCritere()
    }
  }

  /**
   * Message Erreur.
   * @param message
   */
  snackBarError(message: string) {
    this.snackBar.open(message, null, {
      duration: 4000,
      panelClass: ['snack-bar-error']
    });
  }
}
