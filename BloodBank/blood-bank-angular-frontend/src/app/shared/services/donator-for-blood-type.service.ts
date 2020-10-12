import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { DonatorForBloodType } from '../models/donator-for-blood-type';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DonatorForBloodTypeService {

  baseUrl = environment.baseUrl;
  donatorsUrl = this.baseUrl+environment.donatorsUrl;
 
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getDonatorsForBloodType(): Observable<DonatorForBloodType[]> {
    return this.http.get<DonatorForBloodType[]>(`${this.donatorsUrl}`+'/quant-for-each-blood-type').pipe(
      map(response => response)
    )
  }

}
