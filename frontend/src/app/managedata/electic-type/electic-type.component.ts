import { MessageService } from 'primeng/api';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ProductType } from 'src/app/Model/ProductType';
import { ProductTypeService } from 'src/app/services/productType.service';

@Component({
  selector: 'app-electic-type',
  templateUrl: './electic-type.component.html',
  styleUrls: ['./electic-type.component.css']
})
export class ElecticTypeComponent implements OnInit {
  inputForm : any;
  editable : boolean;
  submitted: boolean;
  productDialog: boolean;
  pageModel: any;
  loading : boolean;
  deleteDialog: boolean;
  constructor(
    private fb: FormBuilder,
    private productTypeService : ProductTypeService,
    private messageService : MessageService
  ) { }

  ngOnInit(): void {
    this.buildForm();
    this.getAllProductType();
  }

  openNew() {
    this.buildForm();
    this.inputForm.enable()
    this.editable = true;
    this.submitted = false;
    this.productDialog = true;
  }

  buildForm(){
    this.inputForm = this.fb.group({
      productTypeId: [''],
      productName: ['', Validators.required],
    });
  }

  getAllProductType() {
    this.loading =true;
    this.productTypeService.getAllProductType().then((response) => {
      this.pageModel = response;
    });
    this.loading =false;
  }

  editProduct(product: ProductType) {
    this.editable = true;
    this.inputForm.enable()
    this.inputForm.patchValue(product);
    this.productDialog = true;
}

deletProduct(product : ProductType){
  this.inputForm.enable()
  this.inputForm.patchValue(product);
  this.deleteDialog = true;
}

hideDeleteDialog() {
  this.deleteDialog = false;
}

prepareSaveData(): ProductType {
  const data = this.inputForm.value;
  const product: ProductType = {
    productTypeId : data.productTypeId,
    productName: data.productName,
  };
  return product;
}


confirmDelete(){
  const product = this.prepareSaveData()
  this.productTypeService.deleteProduct(product).then((response) => {
    if (response.statusCodeValue===200) {
      this.hideDeleteDialog()
      this.ngOnInit();
      this.messageService.add({
        severity: 'success',
        summary: 'สำเร็จ',
        detail: 'ลบรายการสำเร็จ',
      });
    } else if (response.statusCodeValue===226){
      this.hideDeleteDialog()
      this.messageService.add({
        severity: 'error',
        summary: 'เกิดข้อผิดพลาด',
        detail: 'มีการใช้เครื่องใช้ไฟฟ้านี้ในระบบ',
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

onSubmit() {
  if (this.inputForm.valid) {
    const product = this.prepareSaveData();
    this.productTypeService.saveOrUpdateProduct(product).then((response) => {
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

hideDialog() {
  this.productDialog = false;
  this.submitted = false;
}



}
