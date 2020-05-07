import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CreationForm} from "./models/creation-form";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EntiteService {

  private baseUrl = " http://localhost:8080/";

  constructor(private http: HttpClient) {
  }

  creationEntite(form: CreationForm) : Observable<any> {
    return this.http.post<CreationForm>(this.baseUrl + 'entite/creation', form );
  }

}
