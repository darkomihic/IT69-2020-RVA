import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Korisnik_usluge } from '../../models/korisnik_usluge';
import { KorisnikUslugeService } from '../../services/korisnik_usluge.service';
import { Filijala } from 'src/app/models/filijala';
import { Subscription } from 'rxjs';
import { Usluga } from 'src/app/models/usluga';
import { FilijalaService } from 'src/app/services/filijala.service';
import { UslugaService } from 'src/app/services/usluga.service';

@Component({
  selector: 'app-korisnikUsluge-dialog',
  templateUrl: './korisnikUsluge-dialog.component.html',
  styleUrls: ['./korisnikUsluge-dialog.component.css']
})
export class KorisnikUslugeDialogComponent {
  public flagArtDialog!: number;
  private subscription!: Subscription;
  public filijale!: Filijala[];
  public usluge!: Usluga[];

  constructor(public snackBar: MatSnackBar,
    public korisnikUslugeService: KorisnikUslugeService,
    @Inject(MAT_DIALOG_DATA) public data: Korisnik_usluge,
    public dialogRef: MatDialogRef<KorisnikUslugeDialogComponent>,
    public filijalaService: FilijalaService,
    public uslugaService: UslugaService) { }

    ngOnInit() {
      this.subscription = this.filijalaService.getAllFilijala().subscribe(filijalaData => { this.filijale = filijalaData });
      this.subscription = this.uslugaService.getAllUsluga().subscribe(uslugaData => { this.usluge = uslugaData });
    }

    compareTo(a: any, b: any) {
      return a.id == b.id;
    }

  public add(): void {
    console.log("ID je " + this.data.korisnik_uslugeID + this.data.prezime);

  

    this.korisnikUslugeService.addKorisnikUsluge(this.data).subscribe(() => {

      this.snackBar.open('Uspesno dodata korisnikUsluge: ' + this.data.prezime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja nove banke. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {


    this.korisnikUslugeService.updateKorisnikUsluge(this.data).subscribe(() => {
      this.snackBar.open('Uspesno izmenjen korisnikUsluge: ' + this.data.prezime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene banke. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    console.log("idkorisnikUsluge:"+this.data.korisnik_uslugeID)
    this.korisnikUslugeService.deleteKorisnikUsluge(this.data.korisnik_uslugeID).subscribe(() => {
      this.snackBar.open('Uspesno obrisana korisnikUsluge: ' + this.data.prezime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja filijale. ', 'Zatvori', {
          duration: 2500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
      duration: 1000
    })
  }
}