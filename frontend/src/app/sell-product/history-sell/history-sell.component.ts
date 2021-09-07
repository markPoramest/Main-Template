import { DropDownService } from './../../services/dropDown.service';
import { SellService } from './../../services/sell.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Sell } from 'src/app/Model/Sell';
import { FormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-history-sell',
  templateUrl: './history-sell.component.html',
  styleUrls: ['./history-sell.component.css']
})
export class HistorySellComponent implements OnInit {
  loading: any;
  pageModel: any;
  productTypeFilter : any [] = [];
  brandFilter : any [] = [];
  conditionFilter = ['สินค้ามือ 1','สินค้ามือ 2']
  constructor(
    private sellService: SellService,
    private dropdDownService : DropDownService,
  ) {}

  async ngOnInit() {
    this.loading = true;
    await this.getAllSellHistory();
    await this.getBrand()
    await this.getProductType()
    this.loading = false;
  }

  getProductType() {
    this.dropdDownService.getproductTypeDropDpwn().then((response) => {
      for(let i=0;i<response.length;i++){
        this.productTypeFilter.push(response[i].desc)
      }
    });
  }

  getBrand() {
    this.dropdDownService.getBrandDropDpwn().then((response) => {
      for(let i=0;i<response.length;i++){
        this.brandFilter.push(response[i].desc)
      }
    });
  }

  getAllSellHistory() {
    this.sellService.getAllSellHistory().then((response) => {
      this.pageModel = response;
    });
  }

}
