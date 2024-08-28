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
import { Banka } from '../../models/banka';
import { BankaService } from '../../services/banka.service';
import { BankaDialogComponent } from '../../dialogs/banka-dialog/banka-dialog.component';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent {
  subscription!: Subscription;
  displayedColumns = ['bankaid', 'naziv', 'kontakt', 'pib', 'actions'];
  data!: MatTableDataSource<Banka>;
  selektovanaBanka!: Banka;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private bankaService: BankaService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadData();
  }

  ngOnChanges(): void {
    this.loadData(); 
  }


  loadData(): void {
    this.bankaService.getAllBanka().subscribe(
      data => {
        // Log the data to the console
        console.log('Data received from getAllBanka:', data);
        
        this.data = new MatTableDataSource(data);
        this.data.sort = this.sort;
        this.data.paginator = this.paginator;
      },
      error => {
        console.log('Error:', error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag: number, banka?: Banka): void {
    console.log('Banka passed to dialog:', banka);
    const dialogRef = this.dialog.open(BankaDialogComponent, {
      data: (banka ? banka : new Banka())
    });

    dialogRef.componentInstance.flagArtDialog = flag;

    dialogRef.afterClosed().subscribe(res => {
      if (res == 1) this.loadData();
    });
}


  selectRow(row: Banka) {
    this.selektovanaBanka = row;
    console.log("click")
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.data.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }


  
}
