import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Entity';
  log = sessionStorage.getItem('log');
  prenom= sessionStorage.getItem('prenom');
  nom = sessionStorage.getItem('nom');
}
