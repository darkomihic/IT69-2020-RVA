import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BankaComponent } from './components/banka/banka.component';
import { Korisnik_uslugeComponent } from './components/korisnik-usluge/korisnik-usluge.component';
import { FilijalaComponent } from './components/filijala/filijala.component';

const routes: Routes = [ { path: 'banka', component: BankaComponent },   
  { path: 'korisnik_usluge', component: Korisnik_uslugeComponent },
  { path: 'filijala', component: FilijalaComponent },
  { path: '', redirectTo: '/pocetna', pathMatch: 'full'}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
