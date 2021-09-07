import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagedataComponent } from './managedata.component';

describe('ManagedataComponent', () => {
  let component: ManagedataComponent;
  let fixture: ComponentFixture<ManagedataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagedataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagedataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
