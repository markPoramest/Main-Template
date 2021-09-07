import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Product } from '../Model/Product';
import { Observable } from 'rxjs';
import { mainURL , productAPI } from '../Constants/ApiPath.component';
import { UtitlityService } from './utility.servce';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient , private utitlityService:UtitlityService) { }

  public getAllProduct():Promise<any>{
    const url = mainURL.url+productAPI.getAllProduct
    return this.http.get<any>(url)
    .toPromise()
    .then(response => response as any);
  }

  public saveOrUpdateProduct(product:Product):Promise<any>{
    const url = mainURL.url+productAPI.saveProduct
    return this.http.post<any>(url,product)
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
