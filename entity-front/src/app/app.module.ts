import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {ReactiveFormsModule} from "@angular/forms";
import { HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule} from "@angular/platform-browser/animations";


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import {RechercheComponent} from "./recherche/recherche.component";
import { AffichageEntiteComponent} from './affichage-entite/affichage-entite.component';
import { ResultatEntiteComponent } from './resultat-entite/resultat-entite.component';
import {MatTableModule} from "@angular/material/table";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from "@angular/material/button";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatDateFormats, MatNativeDateModule} from "@angular/material/core";
import {MatChipsModule} from "@angular/material/chips";
import { HeaderNavBarComponent } from './header-nav-bar/header-nav-bar.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatIconModule} from "@angular/material/icon";
import { FooterComponent } from './footer/footer.component';
import {_MatMenuDirectivesModule, MatMenuModule} from "@angular/material/menu";
import { CreationEntiteComponent } from './creation-entite/creation-entite.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { ConnexionComponent } from './connexion/connexion.component';

import { authInterceptorProviders} from "./helpers/auth.interceptor";
import { ModificationEntiteComponent } from './modification-entite/modification-entite.component';
import { AffichageEntiteConfirmationComponent } from './affichage-entite/affichage-entite-confirmation/affichage-entite-confirmation.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";


export const MY_FORMAT: MatDateFormats = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@NgModule({
  declarations: [
    AppComponent,
    RechercheComponent,
    AffichageEntiteComponent,
    ResultatEntiteComponent,
    HeaderNavBarComponent,
    FooterComponent,
    CreationEntiteComponent,
    ConnexionComponent,
    ModificationEntiteComponent,
    AffichageEntiteConfirmationComponent,
  ],
  entryComponents: [AffichageEntiteConfirmationComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatGridListModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatChipsModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatButtonToggleModule,
    MatIconModule,
    _MatMenuDirectivesModule,
    MatMenuModule,
    MatDialogModule,
    MatSidenavModule,
    MatListModule,
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'fr-FR' },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMAT },
    authInterceptorProviders,
    ],
  bootstrap: [AppComponent]
})
export class AppModule {


}


