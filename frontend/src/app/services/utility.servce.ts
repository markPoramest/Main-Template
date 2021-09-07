import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Product } from '../Model/Product';
import { Observable } from 'rxjs';
import { mainURL , productAPI } from '../Constants/ApiPath.component';
@Injectable({
  providedIn: 'root'
})
export class UtitlityService {

  constructor(private http:HttpClient) { }

  public handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    if (error.status === 404) {
      return Promise.reject('ไม่พบข้อมูล')
    }
    return Promise.reject('เกิดข้อผิดพลาด');
  }
}
