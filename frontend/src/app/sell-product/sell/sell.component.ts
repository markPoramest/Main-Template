import { SellService } from './../../services/sell.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { DropDown } from 'src/app/Model/DropDown';
import { Product } from 'src/app/Model/Product';
import { Sell } from 'src/app/Model/Sell';
import { DropDownService } from 'src/app/services/dropDown.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css']
})
export class SellComponent implements OnInit {

  loading: any;
  pageModel: any;
  productTypeList: DropDown[] = [];
  products: Product[];
  product: Product;
  submitted: boolean;
  brandList: any[] = [];
  productDialog: boolean;
  sellDialog : boolean;
  productTypeFilter : any [] = [];
  brandFilter : any [] = [];
  warrantyList: any = [
    { id: 1, desc: 1 },
    { id: 3, desc: 3 },
    { id: 6, desc: 6 },
    { id: 9, desc: 9 },
    { id: 12, desc: 12 },
  ];
  fullPaymentMethod : any = [
    { id: 1, desc: "เงินสด" },
    { id: 3, desc: "โอนเข้าบัญชีธนาคาร" },
    { id: 6, desc: "บัตรเครดิต/เดบิต" }, ]
  conditionList : DropDown[];
  conditionFilter = ['สินค้ามือ 1','สินค้ามือ 2']
  inputForm : any;
  inputFormFullPayment : any
  inputFormInstallment : any

  constructor(
    private router: Router,
    private productService: ProductService,
    private drownDownService: DropDownService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private sellService : SellService
  ) {}

  async ngOnInit() {
    this.loading = true;
    await this.buildForm();
    await this.buildFormFullPayment();
    await this.buildFormInstallment();
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
      productType: [''],
      brand: ['', Validators.required],
      brandName : [''],
      price: [null, Validators.required],
      amount: [null, Validators.required],
      description: [''],
      condition: [null, Validators.required],
      conditionDesc: [null,],
      warranty: [null, Validators.required],
      paymentMethod : [null, Validators.required],
      fullPaymentMethod : [null],
      interest : [0.00],
      month : [1],
      payPerMonth :[0.00],
      customerId : [null , Validators.required],
      customerType: [null, Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      idCardNo: ['', Validators.required],
      tel: ['', Validators.required],
      address: ['', Validators.required],
    });
  }

  buildFormInstallment(){
    this.inputFormInstallment = this.fb.group({
      productId: [''],
      paymentMethod : [null, Validators.required],
      interest : [0.00 , Validators.required],
      month : [1 , Validators.required],
      payPerMonth :[0.00 , Validators.required],

      customerType: [null, Validators.required],
      customerId : [null],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      idCardNo: ['', Validators.required],
      tel: ['', Validators.required],
      address: ['', Validators.required],
    });
  }

  buildFormFullPayment(){
    this.inputFormFullPayment = this.fb.group({
      productId: [''],
      paymentMethod : [null, Validators.required],
      fullPaymentMethod : [null , Validators.required],
      tel: ['', Validators.required],
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
    if(this.inputForm.value.paymentMethod==='1'){
      this.inputFormFullPayment.controls['productId'].setValue(this.inputForm.value.productId)
      this.inputFormFullPayment.controls['paymentMethod'].setValue(this.inputForm.value.paymentMethod)
      this.inputFormFullPayment.controls['fullPaymentMethod'].setValue(this.inputForm.value.fullPaymentMethod)
      this.inputFormFullPayment.controls['tel'].setValue(this.inputForm.value.tel)
      if(this.inputFormFullPayment.valid){
        const sell = this.prepareSaveData();
        this.sellService.saveOrUpdateSell(sell).then((response) => {
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
      }else {
        this.messageService.add({
          severity: 'error',
          summary: 'เกิดข้อผิดพลาด',
          detail: 'กรุณากรอกข้อมูลให้ครบถ้วน',
        });
      }
    }else{
      this.inputFormInstallment.patchValue(this.inputForm.value)
      if(this.inputFormInstallment.valid){
        const sell = this.prepareSaveDataInstallment();
        this.sellService.saveOrUpdateSell(sell).then((response) => {
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
      }else {
        this.messageService.add({
          severity: 'error',
          summary: 'เกิดข้อผิดพลาด',
          detail: 'กรุณากรอกข้อมูลให้ครบถ้วน',
        });
      }
    }
   }

  prepareSaveData(): Sell {
    const data = this.inputFormFullPayment.value;
    const sellFull: Sell = {
      productId : data.productId,
      paymentMethod : data.paymentMethod,
      fullPaymentMethod : data.fullPaymentMethod,
      tel : data.tel
    };
    return sellFull;
  }

  prepareSaveDataInstallment(): Sell {
    const data = this.inputFormInstallment.value;
    const sellFull: Sell = {
      customerId : data.customerId,
      productId : data.productId,
      paymentMethod : data.paymentMethod,
      fullPaymentMethod : data.fullPaymentMethod,
      tel : data.tel,
      interest : data.interest,
      month : data.month,
      payPerMonth : data.payPerMonth,
      customerType : data.customerType,
      firstName : data.firstName,
      lastName : data.lastName,
      idCardNo : data.idCardNo,
      address : data.address,
    };
    return sellFull;
  }

  hideDialog() {
    this.productDialog = false;
    this.sellDialog = false;
    this.submitted = false;
  }

  onSearch() {
    const idCardNo = this.inputForm.value.idCardNo
    this.sellService.getCutomerByIdCardNo(idCardNo).then((response) => {
      if (response.statusCodeValue===200) {
        this.inputForm.controls['firstName'].setValue(response.body.firstName)
        this.inputForm.controls['lastName'].setValue(response.body.lastName)
        this.inputForm.controls['tel'].setValue(response.body.tel)
        this.inputForm.controls['address'].setValue(response.body.address)
        this.inputForm.controls['customerId'].setValue(response.body.customerId)
      }else{
        this.messageService.add({
          severity: 'error',
          summary: 'เกิดข้อผิดพลาด',
          detail: 'ไม่พบข้อมูลในระบบ',
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

  onInterestChange($event: any) {
    this.inputForm.controls['interest'].setValue($event.value)
    let interest = this.inputForm.value.interest/100*this.inputForm.value.month/12*this.inputForm.value.price
    let interestAll =  this.inputForm.value.price+interest

    let perMonth = interestAll/this.inputForm.value.month
    this.inputForm.controls['payPerMonth'].setValue(perMonth);
  }

  onMonthChange($event : any) {
    this.inputForm.controls['month'].setValue($event.value)
    let interest = (this.inputForm.value.interest/100*this.inputForm.value.month/12)*this.inputForm.value.price

    let interestAll =  this.inputForm.value.price+interest
    let perMonth = interestAll/this.inputForm.value.month
    this.inputForm.controls['payPerMonth'].setValue(perMonth);
  }

sellProduct(product: Product) {
    this.inputForm.enable()
    this.buildForm()
    this.inputForm.patchValue(product);
    this.inputForm.controls['payPerMonth'].setValue(this.inputForm.value.price);
    this.sellDialog = true;
}

viewProduct(product: Product) {
  this.inputForm.patchValue(product);
  this.inputForm.disable()
  this.productDialog = true;
}

onChangePayment(){
this.inputForm.controls['customerType'].setValue(null);
}


}
