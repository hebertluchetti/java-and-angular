import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ObesityByGender } from '../models/obesity-by-gender';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ObesityByGenderService {

  baseUrl = environment.baseUrl;
  donorsUrl = this.baseUrl+environment.donorsUrl;
 
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}
  getObesityByGender(): Observable<ObesityByGender[]> {
    return this.http.get<ObesityByGender[]>(`${this.donorsUrl}`+'/obesity-by-gender').pipe(
      map(response => response)
    )
  }
}
