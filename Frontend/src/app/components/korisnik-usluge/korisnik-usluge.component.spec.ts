import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KorisnikUslugeComponent } from './korisnik-usluge.component';

describe('KorisnikUslugeComponent', () => {
  let component: KorisnikUslugeComponent;
  let fixture: ComponentFixture<KorisnikUslugeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KorisnikUslugeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KorisnikUslugeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
