import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {RechercheService} from "../recherche.service";


@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {

  rechercheForm: FormGroup;
  entite : string[];
  messageError : string;

  constructor(private fb: FormBuilder, private rs: RechercheService) {
  }

  ngOnInit() {
    this.rechercheForm = this.fb.group({
      codeEntite: []
    });
  }

  rechercheParCodeEntite() {
    this.entite = null;
    this.messageError = null;
    this.rs.rechercheAvecCodeEntite(this.rechercheForm.get('codeEntite').value).subscribe((data : any[]) => {
      console.log(data)
      this.entite = data;
    }, error =>
    { console.log(error)
      this.messageError = error.error.message});
  }


}
