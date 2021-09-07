import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title = 'Example Web Application';
  subTitle = 'By Poramest Vichitnopawan';
  constructor(
    private router: Router,
  ) {}

  ngOnInit(): void {

  }

  gotoMainMenu() {
    this.router.navigate(['main'])
  }

}
