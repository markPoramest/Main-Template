import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellProductComponent } from './sell-product.component';

describe('SellProductComponent', () => {
  let component: SellProductComponent;
  let fixture: ComponentFixture<SellProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SellProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
