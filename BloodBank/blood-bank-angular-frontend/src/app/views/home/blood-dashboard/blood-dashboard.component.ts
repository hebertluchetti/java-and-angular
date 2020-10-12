import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-blood-dashboard',
  templateUrl: './blood-dashboard.component.html',
  styleUrls: ['./blood-dashboard.component.css']
})
export class BloodDashboardComponent implements OnInit {

  dataIsReady: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

}
