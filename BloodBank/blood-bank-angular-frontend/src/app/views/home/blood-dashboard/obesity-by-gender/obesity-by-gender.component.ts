import { AfterViewInit, Component,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatSort } from '@angular/material/sort';

import { MatTableDataSource } from '@angular/material/table';
import { ObesityByGender } from 'src/app/shared/models/obesity-by-gender';
import { ObesityByGenderService } from 'src/app/shared/services/obesity-by-gender.service';


@Component({
  selector: 'app-obesity-by-gender',
  templateUrl: './obesity-by-gender.component.html',
  styleUrls: ['./obesity-by-gender.component.css']
})
export class ObesityByGenderComponent implements AfterViewInit {

  obesityByGenderList: ObesityByGender[] = [];
  obesityByGender: ObesityByGender = {
    "gender": "",
    "percentage": 0
  };

  genderLabel: string = "Gênero";
  percentageLabel: string = "Obesidade";
  titleLabel: string = "Porcentagem de obesidade por gênero";

  displayedColumns: string[] = [ 'gender', 'percentage'];
  dataSource = new MatTableDataSource(this.obesityByGenderList);

  constructor(
    private obesityByGenderService: ObesityByGenderService, 
    private router: Router) {}

  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.getObesityByGender();
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  getObesityByGender() {
    this.obesityByGenderService.getObesityByGender().subscribe(
      data => this.obesityByGenderList = data
    )
  }
}
