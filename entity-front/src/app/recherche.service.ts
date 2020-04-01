import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";



@Injectable({
  providedIn: 'root'
})
export class RechercheService {

  private baseUrl = " http://localhost:8080/";


  constructor(private http: HttpClient) {
  }

  rechercheAvecCodeEntite(code) {
   // this.http.get(`${this.baseUrl}entite?id=${code}`).subscribe((data) => {console.log(data);});
  return  this.http.get(`${this.baseUrl}entite?id=${code}`);
  }


}
