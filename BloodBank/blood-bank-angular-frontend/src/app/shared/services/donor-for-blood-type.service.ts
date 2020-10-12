import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { DonorForBloodType } from '../models/donor-for-blood-type';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DonorForBloodTypeService {

  baseUrl = environment.baseUrl;
  donorsUrl = this.baseUrl+environment.donorsUrl;
 
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getDonorsForBloodType(): Observable<DonorForBloodType[]> {
    return this.http.get<DonorForBloodType[]>(`${this.donorsUrl}`+'/quant-for-each-blood-type').pipe(
      map(response => response)
    )
  }

}
