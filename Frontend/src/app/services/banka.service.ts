import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { URL } from '../../app.constants';
import { Banka } from '../models/banka';

@Injectable({
  providedIn: 'root'
})
export class BankaService {

  constructor(private httpClient: HttpClient) { }

  public getAllBanka(): Observable<any> {
    return this.httpClient.get(URL+"/banka");
  }

  public addBanka(banka: Banka): Observable<any>{
    return this.httpClient.post(URL+"/banka", banka);
  }

  public updateBanka(banka: Banka): Observable<any>{
    return this.httpClient.put(URL+"/banka/"+banka.bankaID, banka);
  }

  public deleteBanka(idbanka: number): Observable<any>{
    return this.httpClient.delete(URL+"/banka/"+idbanka);
  }
  
}
