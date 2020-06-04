import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificationEntiteComponent } from './modification-entite.component';

describe('ModificationEntiteComponent', () => {
  let component: ModificationEntiteComponent;
  let fixture: ComponentFixture<ModificationEntiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModificationEntiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificationEntiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
