import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { URL } from '../../app.constants';
import { Usluga } from '../models/usluga';

@Injectable({
  providedIn: 'root'
})
export class UslugaService {

  constructor(private httpClient: HttpClient) { }

  public getAllUsluga(): Observable<any> {
    return this.httpClient.get(URL + "/usluga");
  }

  public addUsluga(usluga: Usluga): Observable<any>{
    return this.httpClient.post(URL + "/usluga", usluga);
  }

  public updateUsluga(usluga: Usluga): Observable<any>{
    return this.httpClient.put(URL + "/usluga/" + usluga.uslugaID, usluga);
  }

  public deleteUsluga(idusluga: number): Observable<any>{
    return this.httpClient.delete(URL + "/usluga/" + idusluga);
  }
  
}
