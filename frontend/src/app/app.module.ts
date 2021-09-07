import { ProductModule } from './product/product.module';
import {  NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { LoginComponent } from './login/login.component';
import {ConfirmationService, ConfirmEventType, MessageService} from 'primeng/api';
import { SharedModule } from './shared.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { ManagedataComponent } from './managedata/managedata.component';
import { ElecticTypeComponent } from './managedata/electic-type/electic-type.component';
import { BrandComponent } from './managedata/brand/brand.component';
import { SellProductComponent } from './sell-product/sell-product.component';
import { SellComponent } from './sell-product/sell/sell.component';
import { CustomerServiceComponent } from './sell-product/customer-service/customer-service.component';
import { HistorySellComponent } from './sell-product/history-sell/history-sell.component';
@NgModule({
  declarations: [
    AppComponent,
    MainMenuComponent,
    LoginComponent,
    ManagedataComponent,
    ElecticTypeComponent,
    BrandComponent,
    SellProductComponent,
    SellComponent,
    CustomerServiceComponent,
    HistorySellComponent,
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    ProductModule,
    SharedModule
  ],
  providers: [ConfirmationService , MessageService],
  bootstrap: [AppComponent],
})
export class AppModule { }
