import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatWrapperComponent } from './stat-wrapper.component';

describe('StatWrapperComponent', () => {
  let component: StatWrapperComponent;
  let fixture: ComponentFixture<StatWrapperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatWrapperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatWrapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
