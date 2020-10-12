import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { DonorByState } from '../models/donor-by-state';
import { environment } from '../../../environments/environment';


@Injectable({
  providedIn: 'root'
})

export class DonorByStateService {

  baseUrl = environment.baseUrl;
  donorsUrl = this.baseUrl+environment.donorsUrl;
 
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getDonorsByState(): Observable<DonorByState[]> {
    return this.http.get<DonorByState[]>(`${this.donorsUrl}`+'/quant-by-state').pipe(
      map(response => response)
    )
  }
}
