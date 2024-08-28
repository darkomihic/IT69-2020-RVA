import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Filijala } from '../../models/filijala';
import { FilijalaService } from '../../services/filijala.service';
import { Subscription } from 'rxjs';
import { Banka } from 'src/app/models/banka';
import { BankaService } from 'src/app/services/banka.service';

@Component({
  selector: 'app-filijala-dialog',
  templateUrl: './filijala-dialog.component.html',
  styleUrls: ['./filijala-dialog.component.css']
})
export class FilijalaDialogComponent {

  public flagArtDialog!: number;
  private subscription!: Subscription;
  public banke!: Banka[];


  constructor(public snackBar: MatSnackBar,
    public filijalaService: FilijalaService,
    @Inject(MAT_DIALOG_DATA) public data: Filijala,
    public dialogRef: MatDialogRef<FilijalaDialogComponent>,
    public bankaService: BankaService) { }

  
    ngOnInit() {
      this.subscription = this.bankaService.getAllBanka().subscribe(bankaData => { this.banke = bankaData });
  
    }

    compareTo(a: any, b: any) {
      return a.id == b.id;
    }

    public add(): void {
      console.log("oivde smo", JSON.stringify(this.data, null, 2));
  
      this.filijalaService.addFilijala(this.data).subscribe(() => {
        this.snackBar.open('Uspesno dodata filijala: ' + this.data.adresa, 'OK', {
          duration: 2500
        });
      },
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Doslo je do greske prilikom dodavanja nove banke.', 'Zatvori', {
          duration: 2500
        });
      });
  }
  


  public update(): void {


    this.filijalaService.updateFilijala(this.data).subscribe(() => {
      this.snackBar.open('Uspesno izmenjen filijala: ' + this.data.adresa, 'OK', {
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
    console.log("idfilijala:"+this.data.filijalaID)
    this.filijalaService.deleteFilijala(this.data.filijalaID).subscribe(() => {
      this.snackBar.open('Uspesno obrisana filijala: ' + this.data.adresa, 'OK', {
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