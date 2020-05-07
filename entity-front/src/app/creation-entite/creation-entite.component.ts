import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {FormulaireService} from "../formulaire.service";
import {EntiteService} from "../entite.service";
import {Metier} from "../models/Metier";
import {Ville} from "../models/Ville";
import {Activite} from "../models/Activite";
import {Site} from "../models/Site";
import {CreationForm} from "../models/creation-form";
import {Entite} from "../models/Entite";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {element} from "protractor";


@Component({
  selector: 'app-creation-entite',
  templateUrl: './creation-entite.component.html',
  styleUrls: ['./creation-entite.component.css']
})
export class CreationEntiteComponent implements OnInit {

  creationForm: FormGroup;
  newEntite: Entite;
  codeEntiteMere: string;
  messageError: string;
  villePhysique: number;
  metier: Metier[];
  ville: Ville[];
  activite: Activite[];
  site: Site[];
  isSite: boolean ;
  reponse: boolean;

  constructor(private fb: FormBuilder, private fs: FormulaireService, private es: EntiteService, private router: Router, private snackBar: MatSnackBar){
  }

  ngOnInit(): void {

    this.fs.chargeLesMetiers()
      .subscribe((data: any[]) => {
          this.metier = data;
        },
        error => {
          this.messageError = error.error.message;
          this.openSnackBar(this.messageError);
        });

    this.fs.chargeLesVilles()
      .subscribe((data: any[]) => {
          this.ville = data;
          this.isSite = true;
        },
        error => {
          this.messageError = error.error.message
          this.isSite = false;
          this.openSnackBar(this.messageError);
        });

    this.fs.chargeLesActivites()
      .subscribe((data: any[]) => {
          this.activite = data;
        },
        error => {
          this.messageError = error.error.message;
          this.openSnackBar(this.messageError);
        });


    this.creationForm = this.fb.group({
      libelle: null,
      entiteMere: null,
      metier: null,
      voieAdressePhysique: null,
      cpAdressePhysique: null,
      villePhysique: null,
      voieAdressePostal: null,
      cpAdressePostal: null,
      villePostal: null,
      site: null,
      rh: 'true',
      comptable: 'false',
      activite: null
    });
  }

  initSite(idVille: number) {
    this.fs.chargeLesSites(idVille).subscribe((data: any[]) => {
        this.site = data;
      },
      error => {
        this.messageError = error.error.message;
        this.openSnackBar(this.messageError);
      }, () => {
        this.isSite = this.site.length != 0;
      }
    )
  }

  verifieEntiteMere(codeEntite: string) {
    this.codeEntiteMere = codeEntite;
    this.reponse = null;
    this.messageError = null;

    if (codeEntite.length === 6) {
      this.reponse = false;
      this.fs.verifieEntiteMere(codeEntite).subscribe((data: boolean) => {
          this.reponse = data;
        },
        error => {
          this.messageError = error.error.message;
          this.openSnackBar(this.messageError);
        });
    }
  }


  entiteMereStyle() {
    if (this.reponse != true && this.codeEntiteMere) {
      return 'red';
    }
  }


  creationEntite() {
    const formValue = this.creationForm.value;
    const newForm = new CreationForm(
      formValue['libelle'],
      formValue['entiteMere'],
      formValue['metier'],
      formValue['voieAdressePhysique'],
      formValue['cpAdressePhysique'],
      formValue['villePhysique'],
      formValue['voieAdressePostal'],
      formValue['cpAdressePostal'],
      formValue['villePostal'],
      formValue['site'],
      formValue['rh'],
      formValue['comptable'],
      formValue['activite']
    );
    this.es.creationEntite(newForm).subscribe((data: Entite) => {
        this.newEntite = data;
        setTimeout(
          () => {
            this.router.navigateByUrl(`/entite/${this.newEntite.codeEntite}`);
          }, 2000
        );
      },
      error => {
        this.messageError = error.error.message
        this.openSnackBar(this.messageError);
      });
  }

  reinitialiser() {
    this.creationForm.get('libelle').setValue(null);
    this.creationForm.get('entiteMere').setValue(null);
    this.creationForm.get('metier').setValue(null);
    this.creationForm.get('voieAdressePhysique').setValue(null);
    this.creationForm.get('cpAdressePhysique').setValue(null);
    this.creationForm.get('villePhysique').setValue(null);
    this.creationForm.get('voieAdressePostal').setValue(null);
    this.creationForm.get('cpAdressePostal').setValue(null);
    this.creationForm.get('villePostal').setValue(null);
    this.creationForm.get('site').setValue(null);
    this.creationForm.get('rh').setValue('true');
    this.creationForm.get('comptable').setValue('false');
    this.creationForm.get('activite').setValue(null);
    this.site = null;
    this.messageError = null;
  }

  openSnackBar(message: string) {
    this.snackBar.open(message ,null,{
      duration: 2500,
      panelClass: ['snack-bar']
    });

  }


}
