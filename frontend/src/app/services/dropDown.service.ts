import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';;
import { mainURL , drownDownAPI } from '../Constants/ApiPath.component';
@Injectable({
  providedIn: 'root'
})
export class DropDownService {

  constructor(private http:HttpClient) { }

  public getproductTypeDropDpwn():Promise<any>{
    const url = mainURL.url+drownDownAPI.productTypeDropDpwn
    return this.http.get<any>(url)
    .toPromise()
    .then(response => response as Array<any>);
  }

  public getBrandDropDpwn():Promise<any>{
    const url = mainURL.url+drownDownAPI.brandDropDpwn
    return this.http.get<any>(url)
    .toPromise()
    .then(response => response as Array<any>);
  }
}
