import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultatEntiteComponent } from './resultat-entite.component';

describe('ResultatEntiteComponent', () => {
  let component: ResultatEntiteComponent;
  let fixture: ComponentFixture<ResultatEntiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResultatEntiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultatEntiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
