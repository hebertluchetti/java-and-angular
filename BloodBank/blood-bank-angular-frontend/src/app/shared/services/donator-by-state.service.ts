import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { DonatorByState } from '../models/donator-by-state';
import { environment } from '../../../environments/environment';


@Injectable({
  providedIn: 'root'
})

export class DonatorByStateService {

  baseUrl = environment.baseUrl;
  donatorsUrl = this.baseUrl+environment.donatorsUrl;
 
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getDonatorsByState(): Observable<DonatorByState[]> {
    return this.http.get<DonatorByState[]>(`${this.donatorsUrl}`+'/quant-by-state').pipe(
      map(response => response)
    )
  }
}
