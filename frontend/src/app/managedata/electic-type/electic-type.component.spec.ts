import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElecticTypeComponent } from './electic-type.component';

describe('ElecticTypeComponent', () => {
  let component: ElecticTypeComponent;
  let fixture: ComponentFixture<ElecticTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElecticTypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElecticTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
