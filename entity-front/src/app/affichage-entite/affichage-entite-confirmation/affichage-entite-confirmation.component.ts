import {Component, Inject, OnInit, ViewContainerRef} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {EntiteService} from "../../services/entite.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-affichage-entite-confirmation',
  templateUrl: './affichage-entite-confirmation.component.html',
 // styleUrls: ['./affichage-entite-confirmation.component.css']
})
export class AffichageEntiteConfirmationComponent implements OnInit {

  messageError = '';

  constructor(private entiteService: EntiteService,
              private router: Router,
              private snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<AffichageEntiteConfirmationComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  /**
   * Message de confirmation avant supression
   */
  onDelete() {
    this.entiteService.suppressionEntite(this.data.code).subscribe(next => {
        this.snackBarOk("Suppression réussie !");
        this.dialogRef.close();
        this.router.navigate(['/accueil']);
      },
      error => {
        this.messageError = error.error.message;
        this.snackBarError(this.messageError);
        this.dialogRef.close();
      });
  }

  /**
   * Message ok
   * @param message
   */
  snackBarOk(message: string) {

    this.snackBar.open(message, null, {
      duration: 2200,
      panelClass: ['snack-bar-ok']
    });
  }

  /**
   * Message Erreur
   * @param message
   */
  snackBarError(message: string) {
    this.snackBar.open(message, null, {
      duration: 4000,
      panelClass: ['snack-bar-error'],

    });
  }

}
