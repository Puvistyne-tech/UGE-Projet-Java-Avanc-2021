import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FicheRdvComponent } from './fiche-rdv.component';

describe('FicheRdvComponent', () => {
  let component: FicheRdvComponent;
  let fixture: ComponentFixture<FicheRdvComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FicheRdvComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FicheRdvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
