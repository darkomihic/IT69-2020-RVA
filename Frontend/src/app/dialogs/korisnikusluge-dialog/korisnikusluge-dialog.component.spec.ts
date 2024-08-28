import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KorisnikUslugeDialogComponent } from './korisnikusluge-dialog.component';

describe('KorisnikuslugeDialogComponent', () => {
  let component: KorisnikUslugeDialogComponent;
  let fixture: ComponentFixture<KorisnikUslugeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KorisnikUslugeDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KorisnikUslugeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
