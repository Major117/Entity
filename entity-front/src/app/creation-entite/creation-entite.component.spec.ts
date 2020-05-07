import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreationEntiteComponent } from './creation-entite.component';

describe('CreationEntiteComponent', () => {
  let component: CreationEntiteComponent;
  let fixture: ComponentFixture<CreationEntiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreationEntiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreationEntiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
