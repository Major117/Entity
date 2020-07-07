import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class RoleGuardService implements CanActivate {

  constructor(public router: Router, public tokenStorage: TokenStorageService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    const listRole = route.data.roles;

    if (this.tokenStorage.isLoggedIn()) {
      const userRole = this.tokenStorage.getUser().roles.toString();
      if (listRole.includes(userRole)) {
        return true
      } else {
        return false
      }
    } else {
      this.router.navigate(['connexion'], {queryParams: {returnUrl: state.url}});
      return false
    }
  }

}
