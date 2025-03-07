import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BankaComponent } from './banka.component';

describe('BankaComponent', () => {
  let component: BankaComponent;
  let fixture: ComponentFixture<BankaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BankaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BankaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
