import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Entite} from "../models/Entite";
import {RechercheService} from "../services/recherche.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Metier} from "../models/Metier";
import {Ville} from "../models/Ville";
import {Activite} from "../models/Activite";
import {Site} from "../models/Site";
import {FormulaireService} from "../services/formulaire.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {EntiteService} from "../services/entite.service";
import {CreationForm} from "../models/creation-form";


@Component({
  selector: 'app-modification-entite',
  templateUrl: './modification-entite.component.html',
  styleUrls: ['./modification-entite.component.css']
})
export class ModificationEntiteComponent implements OnInit {

  code: string;
  entite: Entite;
  newEntite: Entite;

  modificationForm: FormGroup;
  codeEntiteMere: string;
  messageError: string;
  metier: Metier[];
  ville: Ville[];
  activite: Activite[];
  site: Site[];
  isSite: boolean;
  reponse: boolean;



  constructor(private route: ActivatedRoute,
              private rechercheService: RechercheService,
              private formBuilder: FormBuilder,
              private formService: FormulaireService,
              private snackBar: MatSnackBar,
              private entiteService: EntiteService,
              private router: Router) {

    this.modificationForm = this.formBuilder.group({
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
      rh: null,
      comptable: null,
      activite: null
    });

  }

  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      this.code = params['code'];
    });

    this.rechercheService.rechercheAvecCodeEntite(this.code).subscribe((data: Entite) => {
      this.entite = data;
    },error => {
      this.messageError = error.error.message;
      this.snackBarError(this.messageError);
    }, () => this.valeurInitialEntite());

    this.formService.chargeLesMetiers()
      .subscribe((data: any[]) => {
          this.metier = data;
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });

    this.formService.chargeLesVilles()
      .subscribe((data: any[]) => {
          this.ville = data;
          this.isSite = true;
        },
        error => {
          this.messageError = error.error.message;
          this.isSite = false;
          this.snackBarError(this.messageError);
        });

    this.formService.chargeLesActivites()
      .subscribe((data: any[]) => {
          this.activite = data;
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });

  }


  initSite(idVille: number) {
    this.formService.chargeLesSites(idVille).subscribe((data: any[]) => {
        this.site = data;
      },
      error => {
        this.messageError = error.error.message;
        this.snackBarError(this.messageError);
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
      this.formService.verifieEntiteMere(codeEntite).subscribe((data: boolean) => {
          this.reponse = data;
        },
        error => {
          this.messageError = error.error.message;
          this.snackBarError(this.messageError);
        });
    }
  }

  entiteMereStyle() {
    if (this.reponse != true && this.codeEntiteMere) {
      return 'red';
    }
  }

  valeurInitialEntite() {

    const listActivite : number[] = this.entite.activites.map(({idActivite}) => idActivite);
    const ville: number = this.entite.villePhysique.idVille;

    this.modificationForm.get('libelle').setValue(this.entite.libelle);
    this.modificationForm.get('entiteMere').setValue(this.entite.entiteMere.codeEntite);
    this.modificationForm.get('metier').setValue(this.entite.metier.codeMetier);
    this.modificationForm.get('voieAdressePhysique').setValue(this.entite.voiePhysique);
    this.modificationForm.get('cpAdressePhysique').setValue(this.entite.cpPhysique);
    this.modificationForm.get('villePhysique').setValue(ville);
    this.modificationForm.get('voieAdressePostal').setValue(this.entite?.voiePostale);
    this.modificationForm.get('cpAdressePostal').setValue(this.entite?.cpPostale);
    this.modificationForm.get('villePostal').setValue(this.entite.villePostale?.idVille);
    this.modificationForm.get('site').setValue(this.entite.site?.idSite);
    this.modificationForm.get('rh').setValue(this.entite.rh.toString());
    this.modificationForm.get('comptable').setValue(this.entite.comptable.toString());
    this.modificationForm.get('activite').setValue(listActivite);

    this.initSite(ville);
    this.reponse = true;
    this.site = null;
    this.messageError = null;
  }


  modificationEntite() {
    const formValue = this.modificationForm.value;
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
    console.log(newForm);
    this.entiteService.modificationEntite(this.code, newForm).subscribe((data: Entite) => {
        this.newEntite = data;
        this.snackBarOk('modification réussie');
      setTimeout(
        () => {
          this.router.navigateByUrl(`/entite/${this.newEntite.codeEntite}`);
        }, 2200
      );
      }, error => {
        this.messageError = error.error.message;
        this.snackBarError(this.messageError);
      });
  }

  snackBarOk(message: string) {

    this.snackBar.open(message, null, {
      duration: 2200,
      panelClass: ['snack-bar-ok']
    });
  }

  snackBarError(message: string) {
    this.snackBar.open(message, null, {
      duration: 4000,
      panelClass: ['snack-bar-error']
    });

  }
}
