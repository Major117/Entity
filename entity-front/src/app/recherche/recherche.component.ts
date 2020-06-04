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


  constructor(private fb: FormBuilder,
              private fs: FormulaireService ,
              private rs: RechercheService,
              private router: Router,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {

    this.fs.chargeLesMetiers()
      .subscribe((data: any[]) => {
          this.metier = data;
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });

    this.fs.chargeLesVilles()
      .subscribe((data: any[]) => {
          this.ville = data;
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });

    this.fs.chargeLesActivites()
      .subscribe((data: any[]) => {
          this.activite = data;
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });


    this.rechercheForm = this.fb.group({
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


  rechercheParCodeEntite(code ?: string) {
    this.entite = null;
    this.messageError = null;
    let codeDuForm = this.rechercheForm.get('codeEntite').value;

    if (codeDuForm) {
      code = codeDuForm;
    }

    this.rs.rechercheAvecCodeEntite(code)
      .subscribe(data => { this.entite = data;
        this.router.navigateByUrl(`/entite/${this.entite.codeEntite}`)},
        error => {this.messageError = error.error.message;
                        this.snackBarError(this.messageError);  });

  }


  rechercheMultiCritere() {
    this.listEntite = null;
    this.messageError = null;   //vide les infos

    const formValue = this.rechercheForm.value;
    const newForm = new RechercheForm(
      formValue['codeEntite'],
      formValue['libelle'],
      formValue['metier'],
      formValue['ville'],
      formValue['active'],
      formValue['rh'],
      formValue['date'],
      formValue['activite']
    );
    console.log(newForm);
    this.rs.rechercheMultiCritere(formValue)
      .subscribe((data: any[]) => {
          this.listEntite = data;
          if (this.listEntite.length == 1) {
            this.rechercheParCodeEntite(this.listEntite[0].codeEntite);
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

  snackBarError(message: string) {
    this.snackBar.open(message, null, {
      duration: 4000,
      panelClass: ['snack-bar-error']
    });
  }
}
