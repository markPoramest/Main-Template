import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { mainURL , productTypeAPI } from '../Constants/ApiPath.component';
import { UtitlityService } from './utility.servce';
import { ProductType } from '../Model/ProductType';
@Injectable({
  providedIn: 'root'
})
export class ProductTypeService {

  constructor(private http:HttpClient , private utitlityService:UtitlityService) { }

  public getAllProductType():Promise<any>{
    const url = mainURL.url+productTypeAPI.getAllProductType
    return this.http.get<any>(url)
    .toPromise()
    .then(response => response as any);
  }

  public saveOrUpdateProduct(product:ProductType):Promise<any>{
    const url = mainURL.url+productTypeAPI.saveProductType
    return this.http.post<any>(url,product)
    .toPromise()
    .then(response => response as any)
    .catch(this.utitlityService.handleError);
  }

  public deleteProduct(product:ProductType):Promise<any>{
    const url = mainURL.url+productTypeAPI.deleteProductType
    return this.http.post<any>(url,product)
    .toPromise()
    .then(response => response as any)
    .catch(this.utitlityService.handleError);
  }
}
