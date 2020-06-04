import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RechercheComponent} from "./recherche/recherche.component";
import {AffichageEntiteComponent} from "./affichage-entite/affichage-entite.component";
import {CreationEntiteComponent} from "./creation-entite/creation-entite.component";
import {ConnexionComponent} from "./connexion/connexion.component";
import {RoleGuardService} from "./services/role-guard.service";
import {ModificationEntiteComponent} from "./modification-entite/modification-entite.component";


const routes: Routes = [
    { path: '', redirectTo: '/accueil', pathMatch: 'full' },
    { path: 'accueil', component: RechercheComponent},
    { path: 'entite/:codeEntite' , component: AffichageEntiteComponent},
    { path: 'creation' , component: CreationEntiteComponent, canActivate : [RoleGuardService], data : {roles: ['ROLE_Gestionnaire','ROLE_Admin']}},
    { path: 'connexion' , component: ConnexionComponent},
    { path: 'modification', component: ModificationEntiteComponent, canActivate : [RoleGuardService], data : {roles : ['ROLE_Gestionnaire','ROLE_Admin']}}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
