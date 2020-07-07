import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RechercheComponent} from "./recherche/recherche.component";
import {AffichageEntiteComponent} from "./affichage-entite/affichage-entite.component";
import {CreationEntiteComponent} from "./creation-entite/creation-entite.component";
import {ConnexionComponent} from "./connexion/connexion.component";
import {RoleGuardService} from "./services/role-guard.service";
import {ModificationEntiteComponent} from "./modification-entite/modification-entite.component";
import {AProposComponent} from "./a-propos/a-propos.component";


const routes: Routes = [
    { path: '', redirectTo: '/accueil', pathMatch: 'full' },
    { path: 'accueil', component: RechercheComponent},
    { path: 'entite/:codeEntite' , component: AffichageEntiteComponent},
    { path: 'connexion' , component: ConnexionComponent},
    { path: 'a-propos' , component: AProposComponent },
    { path: 'creation' , component: CreationEntiteComponent,
        canActivate : [RoleGuardService], data : {roles: ['ROLE_Gestionnaire','ROLE_Admin']}},
    { path: 'modification', component: ModificationEntiteComponent,
        canActivate : [RoleGuardService], data : {roles : ['ROLE_Gestionnaire','ROLE_Admin']}},
    { path: '**', redirectTo: '/accueil'}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
