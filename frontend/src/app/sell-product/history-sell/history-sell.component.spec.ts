import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorySellComponent } from './history-sell.component';

describe('HistorySellComponent', () => {
  let component: HistorySellComponent;
  let fixture: ComponentFixture<HistorySellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistorySellComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistorySellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
