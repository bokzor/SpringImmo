import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ImmoStatInterface} from "./immo-stat.interface";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StatService {

  constructor(private http: HttpClient) {

  }

  getStats(): Observable<ImmoStatInterface> {
    return this.http.get<ImmoStatInterface>("//localhost:8080/stats");
  }

  mergeStats(ImmoStatInterface) {

  }

}
