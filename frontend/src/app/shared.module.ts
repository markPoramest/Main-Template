import { TimelineModule } from 'primeng/timeline';
import { MenuBarComponent } from './share-components/menu-bar/menu-bar.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MultiSelectModule } from 'primeng/multiselect';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import {DialogModule} from 'primeng/dialog';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import { InputNumberModule } from 'primeng/inputnumber';
import {SliderModule} from 'primeng/slider';
import {TabMenuModule} from 'primeng/tabmenu';
import {TabViewModule} from 'primeng/tabview';
import {RadioButtonModule} from 'primeng/radiobutton';
@NgModule({
  imports: [CommonModule],
  declarations: [MenuBarComponent],
  exports: [
    MenuBarComponent,
    CommonModule,
    FormsModule,
    ToastModule,
    TableModule,
    MultiSelectModule,
    DropdownModule,
    HttpClientModule,
    FormsModule,
    TimelineModule,
    ReactiveFormsModule,
    DialogModule,
    ConfirmDialogModule,
    InputNumberModule,
    SliderModule,
    TabMenuModule,
    TabViewModule,
    RadioButtonModule
  ],
})
export class SharedModule {}
