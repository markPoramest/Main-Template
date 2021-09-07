import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { FormBuilder, Validators } from '@angular/forms';
import { Brand } from 'src/app/Model/Brand';
import { BrandService } from 'src/app/services/brand.service';

@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit {

  inputForm : any;
  editable : boolean;
  submitted: boolean;
  productDialog: boolean;
  pageModel: any;
  loading : boolean;
  deleteDialog: boolean;
  constructor(
    private fb: FormBuilder,
    private brandService : BrandService,
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
      brandId: [''],
      brandName: ['', Validators.required],
    });
  }

  getAllProductType() {
    this.loading =true;
    this.brandService.getAllBrand().then((response) => {
      this.pageModel = response;
    });
    this.loading =false;
  }

  editProduct(brand: Brand) {
    this.editable = true;
    this.inputForm.enable()
    this.inputForm.patchValue(brand);
    this.productDialog = true;
}

deletProduct(brand : Brand){
  this.inputForm.enable()
  this.inputForm.patchValue(brand);
  this.deleteDialog = true;
}

hideDeleteDialog() {
  this.deleteDialog = false;
}

prepareSaveData(): Brand {
  const data = this.inputForm.value;
  const brand: Brand = {
    brandId : data.brandId,
    brandName: data.brandName,
  };
  return brand;
}


confirmDelete(){
  const brand = this.prepareSaveData()
  this.brandService.deleteBrand(brand).then((response) => {
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
        detail: 'มีการใช้ยี่ห้อนี้ในระบบ',
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
    this.brandService.saveOrUpdateBrand(product).then((response) => {
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
