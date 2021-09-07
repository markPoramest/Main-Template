import { Sell } from './../Model/Sell';
import { customerAPI, sellAPI } from './../Constants/ApiPath.component';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Product } from '../Model/Product';
import { Observable } from 'rxjs';
import { mainURL , productAPI } from '../Constants/ApiPath.component';
import { UtitlityService } from './utility.servce';
@Injectable({
  providedIn: 'root'
})
export class SellService {

  constructor(private http:HttpClient , private utitlityService:UtitlityService) { }

  public getAllProduct():Promise<any>{
    const url = mainURL.url+productAPI.getAllProduct
    return this.http.get<any>(url)
    .toPromise()
    .then(response => response as any);
  }

  public getAllSellHistory():Promise<any>{
    const url = mainURL.url+sellAPI.getAllSellHistory
    return this.http.get<any>(url)
    .toPromise()
    .then(response => response as any);
  }

  public getCutomerByIdCardNo(idCardNo:any):Promise<any>{
    const url = mainURL.url+customerAPI.getCustomerByIdCardNo
    return this.http.get<any>(url+'/'+idCardNo)
    .toPromise()
    .then(response => response as any)
    .catch(this.utitlityService.handleError);
  }

  public saveOrUpdateSell(sell:Sell):Promise<any>{
    const url = mainURL.url+sellAPI.sellProduct
    return this.http.post<any>(url,sell)
    .toPromise()
    .then(response => response as any)
    .catch(this.utitlityService.handleError);
  }

  public deleteProduct(product:Product):Promise<any>{
    const url = mainURL.url+productAPI.deleteProduct
    return this.http.post<any>(url,product)
    .toPromise()
    .then(response => response as any)
    .catch(this.utitlityService.handleError);
  }
}
