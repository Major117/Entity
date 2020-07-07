import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RechercheForm} from "../models/RechercheForm";
import {Entite} from "../models/Entite";



@Injectable({
  providedIn: 'root'
})
export class RechercheService {

  private baseUrl = " http://localhost:8080/";

  constructor(private http: HttpClient) {
  }


  rechercheAvecCodeEntite(code) : Observable<Entite>  {
    return this.http.get<Entite>(`${this.baseUrl}entite/public/unique?id=${code}`);
  }

  rechercheMultiCritere(form : RechercheForm) :Observable<any> {
    return this.http.post<RechercheForm>(this.baseUrl + 'entite/public/multi', form );
  }

}
