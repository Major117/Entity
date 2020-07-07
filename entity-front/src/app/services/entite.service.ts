import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CreationForm} from "../models/creation-form";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class EntiteService {

  private baseUrl = " http://localhost:8080/entite";

  constructor(private http: HttpClient) {
  }

  creationEntite(form: CreationForm) : Observable<any> {
    return this.http.post<CreationForm>(this.baseUrl + '/creation', form);
  }

  modificationEntite( code: string, form: CreationForm ) : Observable<any> {
    return this.http.post(`${this.baseUrl}/modification?code=${code}`, form );
  }

  suppressionEntite(code: string) :Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete?code=${code}`);
  }
}
