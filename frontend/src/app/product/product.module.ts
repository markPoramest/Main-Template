import { ProductComponent } from './product.component';
import { ProductRoutingModule } from "./product-routing.module";
import { NgModule } from '@angular/core';
import { SharedModule } from '../shared.module';

@NgModule({
  imports: [
    ProductRoutingModule,
    SharedModule
  ],
  declarations: [
    ProductComponent,
  ],
})
export class ProductModule { }
