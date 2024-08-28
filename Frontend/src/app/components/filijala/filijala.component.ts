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
import { Filijala } from '../../models/filijala';
import { FilijalaService } from '../../services/filijala.service';
import { FilijalaDialogComponent } from '../../dialogs/filijala-dialog/filijala-dialog.component';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-filijala',
  templateUrl: './filijala.component.html',
  styleUrls: ['./filijala.component.css']
})
export class FilijalaComponent {
  subscription!: Subscription;
  displayedColumns = ['filijalaid', 'adresa', 'broj_pultova', 'poseduje_sef', 'banka', 'actions'];
  data!: MatTableDataSource<Filijala>;
  selektovanaFilijala!: Filijala;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private filijalaService: FilijalaService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadData();
  }

  ngOnChanges(): void {
    this.loadData(); 
  }


  loadData(): void {
    this.filijalaService.getAllFilijala().subscribe(
      data => {
        // Log the data to the console
        console.log('Data received from getAllFilijala:', data);
        
        this.data = new MatTableDataSource(data);
        this.data.sort = this.sort;
        this.data.paginator = this.paginator;
      },
      error => {
        console.log('Error:', error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag: number, filijala?: Filijala): void {
    console.log('Filijala passed to dialog:', filijala);
    const dialogRef = this.dialog.open(FilijalaDialogComponent, {
      data: (filijala ? filijala : new Filijala())
    });

    dialogRef.componentInstance.flagArtDialog = flag;

    dialogRef.afterClosed().subscribe(res => {
      if (res == 1) this.loadData();
    });
}


  selectRow(row: Filijala) {
    this.selektovanaFilijala = row;
    console.log("click")
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.data.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }


  
}
