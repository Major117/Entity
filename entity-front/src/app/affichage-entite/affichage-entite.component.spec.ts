import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AffichageEntiteComponent } from './affichage-entite.component';

describe('AffichageEntiteComponent', () => {
  let component: AffichageEntiteComponent;
  let fixture: ComponentFixture<AffichageEntiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AffichageEntiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AffichageEntiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
