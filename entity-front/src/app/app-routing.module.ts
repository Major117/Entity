import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RechercheComponent} from "./recherche/recherche.component";
import {AffichageEntiteComponent} from "./affichage-entite/affichage-entite.component";
import {CreationEntiteComponent} from "./creation-entite/creation-entite.component";


const routes: Routes = [
    { path: '', redirectTo: '/accueil', pathMatch: 'full' },
    { path: 'accueil', component: RechercheComponent},
    { path: 'entite/:codeEntite' , component: AffichageEntiteComponent},
    { path: 'creation' , component: CreationEntiteComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
