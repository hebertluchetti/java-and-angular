import { AfterViewInit, Component,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {MatSort} from '@angular/material/sort';

import {MatTableDataSource} from '@angular/material/table';
import { AgeAvgByBloodType } from 'src/app/shared/models/age-avg-by-blood-type';
import { AgeAvgByBloodTypeService } from 'src/app/shared/services/age-avg-by-blood-type.service';

@Component({
  selector: 'app-age-avg-by-blood-type',
  templateUrl: './age-avg-by-blood-type.component.html',
  styleUrls: ['./age-avg-by-blood-type.component.css']
})
export class AgeAvgByBloodTypeComponent implements AfterViewInit {

  ageAvgByBloodTypeList: AgeAvgByBloodType[] = [];
  ageAvgByBloodType: AgeAvgByBloodType = {
    "bloodType": "",
    "ageAvg": 0
  };

  bloodTypeLabel: string = "Tipo de Sanguíneo";
  ageAvgLabel: string = "Média Idade";
  titleLabel: string = "Média de idade por tipo de sanguíneo";

  displayedColumns: string[] = [ 'bloodType', 'ageAvg'];

  dataSource = new MatTableDataSource(this.ageAvgByBloodTypeList);

  constructor(
    private ageAvgByBloodTypeService: AgeAvgByBloodTypeService, 
    private router: Router) {}

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.getAgeAvgByBloodTypes();
    this.dataSource.sort = this.sort;
  }

  getAgeAvgByBloodTypes() {
    this.ageAvgByBloodTypeService.getAgeAvgByBloodTypes().subscribe(
      data => this.ageAvgByBloodTypeList = data
    )
  }
}
