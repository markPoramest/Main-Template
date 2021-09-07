import { DropDownService } from './../services/dropDown.service';
import { ProductService } from './../services/product.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { DropDown } from '../Model/DropDown';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Product } from '../Model/Product';
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  loading: any;
  pageModel: any;
  productTypeList: DropDown[] = [];
  products: Product[];
  product: Product;
  submitted: boolean;
  editable : boolean;
  brandList: any[] = [];
  productDialog: boolean;
  deleteDialog: boolean;
  productTypeFilter : any [] = [];
  brandFilter : any [] = [];
  warrantyList: any = [
    { id: 1, desc: 1 },
    { id: 3, desc: 3 },
    { id: 6, desc: 6 },
    { id: 9, desc: 9 },
    { id: 12, desc: 12 },
  ];
  conditionList : DropDown[];
  conditionFilter = ['สินค้ามือ 1','สินค้ามือ 2']
  inputForm : any;

  constructor(
    private router: Router,
    private productService: ProductService,
    private drownDownService: DropDownService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private location: Location
  ) {}

  async ngOnInit() {
    this.loading = true;
    await this.buildForm();
    await this.getProductType();
    await this.getBrand();
    await this.getAllProduct();
    this.loading = false;
    this.conditionList  = [
      { id: 1, desc: "สินค้ามือ 1" },
      { id: 2, desc: "สินค้ามือ 2" },
    ];
  }

  buildForm(){
    this.inputForm = this.fb.group({
      productId: [''],
      productName: ['', Validators.required],
      productTypeId: ['', Validators.required],
      brand: ['', Validators.required],
      price: [null, Validators.required],
      amount: [null, Validators.required],
      description: [''],
      condition: [null, Validators.required],
      warranty: [null, Validators.required],
    });
  }


  getAllProduct() {
    this.productService.getAllProduct().then((response) => {
      this.pageModel = response;
    });
  }

  getProductType() {
    this.drownDownService.getproductTypeDropDpwn().then((response) => {
      this.productTypeList = response;
      for(let i=0;i<response.length;i++){
        this.productTypeFilter.push(response[i].desc)
      }
    });
  }

  getBrand() {
    this.drownDownService.getBrandDropDpwn().then((response) => {
      this.brandList = response;
      for(let i=0;i<response.length;i++){
        this.brandFilter.push(response[i].desc)
      }
    });
  }

  onSubmit() {
    if (this.inputForm.valid) {
      const product = this.prepareSaveData();
      this.productService.saveOrUpdateProduct(product).then((response) => {
        console.log("response.statusCodeValue "+response.statusCodeValue)
        if (response.statusCodeValue===200) {
          this.hideDialog();
          this.ngOnInit();
          this.messageService.add({
            severity: 'success',
            summary: 'สำเร็จ',
            detail: 'บันทึกสำเร็จ',
          });
        }
      }).catch(e=>{
        this.messageService.add({
          severity: 'error',
          summary: e,
          detail: 'เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง',
        });
      });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'เกิดข้อผิดพลาด',
        detail: 'กรุณากรอกข้อมูลให้ครบถ้วน',
      });
    }
  }

  prepareSaveData(): Product {
    const data = this.inputForm.value;
    const product: Product = {
      productId : data.productId,
      productName: data.productName,
      productTypeId: data.productTypeId,
      productType: data.productType,
      brand: data.brand,
      price: data.price,
      amount: data.amount,
      description: data.description,
      warranty: data.warranty,
      condition: data.condition,
    };
    return product;
  }

  goBack() {
    this.location.back();
  }

  hideDialog() {
    this.productDialog = false;
    this.submitted = false;
  }

  hideDeleteDialog() {
    this.deleteDialog = false;
  }

  openNew() {
    this.buildForm();
    this.inputForm.enable()
    this.editable = true;
    this.submitted = false;
    this.productDialog = true;
  }

  editProduct(product: Product) {
    this.editable = true;
    this.inputForm.enable()
    this.inputForm.patchValue(product);
    this.productDialog = true;
}

viewProduct(product: Product) {
  this.editable = false;
  this.inputForm.patchValue(product);
  this.inputForm.disable()
  this.productDialog = true;
}

deletProduct(product : Product){
  this.inputForm.enable()
  this.inputForm.patchValue(product);
  this.deleteDialog = true;
}

confirmDelete(){
  const product = this.prepareSaveData()
  this.productService.deleteProduct(product).then((response) => {
    if (response.statusCodeValue===200) {
      this.hideDeleteDialog()
      this.ngOnInit();
      this.messageService.add({
        severity: 'success',
        summary: 'สำเร็จ',
        detail: 'ลบรายการสำเร็จ',
      });
    }
  }).catch(e=>{
    this.messageService.add({
      severity: 'error',
      summary: e,
      detail: 'เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง',
    });
  });
}
}
