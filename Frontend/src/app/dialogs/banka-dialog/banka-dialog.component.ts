import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Banka } from '../../models/banka';
import { BankaService } from '../../services/banka.service';

@Component({
  selector: 'app-banka-dialog',
  templateUrl: './banka-dialog.component.html',
  styleUrls: ['./banka-dialog.component.css']
})
export class BankaDialogComponent {
  public flagArtDialog!: number;

  constructor(public snackBar: MatSnackBar,
    public bankaService: BankaService,
    @Inject(MAT_DIALOG_DATA) public data: Banka,
    public dialogRef: MatDialogRef<BankaDialogComponent>) { }

  public add(): void {
    console.log("ID je " + this.data.bankaID + this.data.naziv);

  

    this.bankaService.addBanka(this.data).subscribe(() => {

      this.snackBar.open('Uspesno dodata banka: ' + this.data.naziv, 'OK', {
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


    this.bankaService.updateBanka(this.data).subscribe(() => {
      this.snackBar.open('Uspesno izmenjen banka: ' + this.data.naziv, 'OK', {
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
    console.log("idbanka:"+this.data.bankaID)
    this.bankaService.deleteBanka(this.data.bankaID).subscribe(() => {
      this.snackBar.open('Uspesno obrisana banka: ' + this.data.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja banke. ', 'Zatvori', {
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