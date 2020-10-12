import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ImcAvgByAgeRange } from '../models/imc-avg-by-age-range';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ImcAvgByAgeRangeService {

  baseUrl = environment.baseUrl;
  donorsUrl = this.baseUrl+environment.donorsUrl;
 
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  getImcAvgByAgeRanges(): Observable<ImcAvgByAgeRange[]> {
    return this.http.get<ImcAvgByAgeRange[]>(`${this.donorsUrl}`+'/imc-avg-by-age-range').pipe(
      map(response => response)
    )
  }
}
