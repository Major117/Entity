import { Injectable } from '@angular/core';
import {Entite} from "../models/Entite";

@Injectable({
  providedIn: 'root'
})
export class KeepService {

  constructor() { }

  lastSearch : Entite[];
}
