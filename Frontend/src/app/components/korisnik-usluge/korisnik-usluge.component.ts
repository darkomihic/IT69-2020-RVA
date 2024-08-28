import { Component, ViewChild, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { Korisnik_usluge } from '../../models/korisnik_usluge';
import { KorisnikUslugeService } from '../../services/korisnik_usluge.service';
import { KorisnikUslugeDialogComponent } from '../../dialogs/korisnikusluge-dialog/korisnikusluge-dialog.component';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-Korisnik_usluge',
  templateUrl: './korisnik-usluge.component.html',
  styleUrls: ['./korisnik-usluge.component.css']
})
export class Korisnik_uslugeComponent {
  subscription!: Subscription;
  displayedColumns = ['korisnik_uslugeID', 'ime', 'prezime', 'maticni_broj', 'usluga', 'filijala', 'banka', 'actions'];
  data!: MatTableDataSource<Korisnik_usluge>;
  selektovaniKorisnik_usluge!: Korisnik_usluge;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private KorisnikUslugeService: KorisnikUslugeService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadData();
  }

  ngOnChanges(): void {
    this.loadData(); 
  }


  loadData(): void {
    this.KorisnikUslugeService.getAllKorisnikUsluge().subscribe(
      data => {
        // Log the data to the console
        console.log('Data received from getAllKorisnik_usluge:', data);
        
        this.data = new MatTableDataSource(data);
        this.data.sort = this.sort;
        this.data.paginator = this.paginator;
      },
      error => {
        console.log('Error:', error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag: number, korisnik_usluge?: Korisnik_usluge): void {
    console.log('Korisnik_usluge passed to dialog:', Korisnik_usluge);
    const dialogRef = this.dialog.open(KorisnikUslugeDialogComponent, {
      data: (korisnik_usluge ? korisnik_usluge : new Korisnik_usluge())
    });

    dialogRef.componentInstance.flagArtDialog = flag;

    dialogRef.afterClosed().subscribe(res => {
      if (res == 1) this.loadData();
    });
}


  selectRow(row: Korisnik_usluge) {
    this.selektovaniKorisnik_usluge = row;
    console.log("click")
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.data.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }


  
}
