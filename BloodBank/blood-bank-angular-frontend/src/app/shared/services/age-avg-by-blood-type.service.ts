import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AgeAvgByBloodType } from '../models/age-avg-by-blood-type';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AgeAvgByBloodTypeService {

  baseUrl = environment.baseUrl;
  donatorsUrl = this.baseUrl+environment.donatorsUrl;
 
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  getAgeAvgByBloodTypes(): Observable<AgeAvgByBloodType[]> {
    return this.http.get<AgeAvgByBloodType[]>(`${this.donatorsUrl}`+'/age-avg-by-blood-type').pipe(
      map(response => response)
    )
  }
}
