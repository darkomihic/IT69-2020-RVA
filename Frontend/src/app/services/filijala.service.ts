import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { URL } from '../../app.constants';
import { Filijala } from '../models/filijala';

@Injectable({
  providedIn: 'root'
})
export class FilijalaService {

  constructor(private httpClient: HttpClient) { }

  public getAllFilijala(): Observable<any> {
    return this.httpClient.get(URL + "/filijala");
  }

  public addFilijala(filijala: Filijala): Observable<any>{
    return this.httpClient.post(URL + "/filijala", filijala);
  }

  public updateFilijala(filijala: Filijala): Observable<any>{
    return this.httpClient.put(URL + "/filijala/" + filijala.filijalaID, filijala);
  }

  public deleteFilijala(idfilijala: number): Observable<any>{
    return this.httpClient.delete(URL + "/filijala/" + idfilijala);
  }
  
}
