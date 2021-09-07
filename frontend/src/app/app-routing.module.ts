import { SellProductComponent } from './sell-product/sell-product.component';
import { ManagedataComponent } from './managedata/managedata.component';
import { ProductComponent } from './product/product.component';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', component: LoginComponent },
  {
    path: 'main',
    component: MainMenuComponent
  },
    { path: 'main/product',  loadChildren: ()=>  import('./product/product.module').then(m => m.ProductModule) },
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full'
  },
  { path: 'main/manage-data',  component : ManagedataComponent },
  { path: 'main/sell-product',  component : SellProductComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
