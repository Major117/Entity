import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(private route: Router) { }

  /**
   * Permet a l'utilisateur de se déconnecter
   */
  signOut() {
    window.sessionStorage.clear();
    this.route.navigate(['accueil']);
  }

  /**
   * Enregiste le token dans le sS.
   * @param token
   */
  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  /**
   * Retourne le token.
   */
  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  /**
   * Enregistre l'utilisateur dans le sS.
   * @param user
   */
  public saveUser(user) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  /**
   * Récupére les informations de l'utilisateur.
   */
  public getUser() {
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }

  /**
   * Vérifie si un utilisteur est connecté.
   */
  public isLoggedIn() {
    return this.getUser() !== null
  }
}
