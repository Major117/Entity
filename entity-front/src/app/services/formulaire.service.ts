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
    return this.http.get(`${this.baseUrl}entite/init-metier`);
  }

  chargeLesVilles() : Observable<any> {
    return this.http.get(`${this.baseUrl}entite/init-ville`);
  }

  chargeLesActivites() : Observable<any> {
    return this.http.get(`${this.baseUrl}entite/init-activite`);
  }

  chargeLesSites(idVille: number) : Observable<any> {
    return this.http.get(`${this.baseUrl}entite/init-site?id=${idVille}`  );
  }

  verifieEntiteMere(codeEntite: string) : Observable<any> {
    return this.http.get(`${this.baseUrl}entite/entite-mere?code=${codeEntite}`);
  }


}
