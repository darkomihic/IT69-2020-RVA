import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { URL } from '../../app.constants';
import { Korisnik_usluge } from '../models/korisnik_usluge';

@Injectable({
  providedIn: 'root'
})
export class KorisnikUslugeService {

  constructor(private httpClient: HttpClient) { }

  public getAllKorisnikUsluge(): Observable<any> {
    return this.httpClient.get(URL + "/korisnik_usluge");
  }

  public addKorisnikUsluge(korisnikUsluge: Korisnik_usluge): Observable<any>{
    return this.httpClient.post(URL + "/korisnik_usluge", korisnikUsluge);
  }

  public updateKorisnikUsluge(korisnikUsluge: Korisnik_usluge): Observable<any>{
    return this.httpClient.put(URL + "/korisnik_usluge/" + korisnikUsluge.korisnik_uslugeID, korisnikUsluge);
  }

  public deleteKorisnikUsluge(idkorisnikUsluge: number): Observable<any>{
    return this.httpClient.delete(URL + "/korisnik_usluge/" + idkorisnikUsluge);
  }
  
}
