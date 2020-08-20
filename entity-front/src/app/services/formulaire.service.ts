import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class FormulaireService {

  private baseUrl = " http://localhost:8080/";


  constructor(private http: HttpClient) {
  }

  chargeLesMetiers() : Observable<any> {
    return this.http.get(`${this.baseUrl}init/metier`);
  }

  chargeLesVilles(lettre: string) : Observable<any> {
    return this.http.get(`${this.baseUrl}init/ville?ville=${lettre}`);
  }

  chargeLesActivites() : Observable<any> {
    return this.http.get(`${this.baseUrl}init/activite`);
  }

  chargeLesSites(idVille: number) : Observable<any> {
    return this.http.get(`${this.baseUrl}init/site?id=${idVille}`);
  }

  verifieEntiteMere(codeEntite: string) : Observable<any> {
    return this.http.get(`${this.baseUrl}entite/public/entite-mere?code=${codeEntite}`);
  }


}
