import { Brand } from './../Model/Brand';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Product } from '../Model/Product';
import { Observable } from 'rxjs';
import { brandAPI, mainURL , productAPI, productTypeAPI } from '../Constants/ApiPath.component';
import { UtitlityService } from './utility.servce';
import { ProductType } from '../Model/ProductType';
@Injectable({
  providedIn: 'root'
})
export class BrandService {

  constructor(private http:HttpClient , private utitlityService:UtitlityService) { }

  public getAllBrand():Promise<any>{
    const url = mainURL.url+brandAPI.getAllBrand
    return this.http.get<any>(url)
    .toPromise()
    .then(response => response as any);
  }

  public saveOrUpdateBrand(brand:Brand):Promise<any>{
    const url = mainURL.url+brandAPI.saveBrand
    return this.http.post<any>(url,brand)
    .toPromise()
    .then(response => response as any)
    .catch(this.utitlityService.handleError);
  }

  public deleteBrand(brand:Brand):Promise<any>{
    const url = mainURL.url+brandAPI.deleteBrand
    return this.http.post<any>(url,brand)
    .toPromise()
    .then(response => response as any)
    .catch(this.utitlityService.handleError);
  }
}
