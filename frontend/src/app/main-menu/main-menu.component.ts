import { Component, OnInit } from '@angular/core';
import {MenuItem, PrimeIcons} from 'primeng/api';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

  events!: any[];

  ngOnInit(): void {
    this.events = [
      {status: 'เก็บความต้องการ',  icon: PrimeIcons.ALIGN_LEFT, color: '#9C27B0', image: 'game-controller.jpg'},
      {status: 'ออกแบบ', icon: PrimeIcons.COG, color: '#673AB7'},
      {status: 'พัฒนา', icon: PrimeIcons.ENVELOPE, color: '#FF9800'},
      {status: 'ลูกค้าตรวจสอบ',icon: PrimeIcons.CHECK, color: '#607D8B'},
      {status: 'ส่งงาน',icon: PrimeIcons.CHECK, color: '#607D8B'}
  ];
  }

}
