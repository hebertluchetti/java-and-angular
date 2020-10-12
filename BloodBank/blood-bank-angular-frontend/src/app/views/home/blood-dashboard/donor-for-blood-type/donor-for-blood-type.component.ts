import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {MatSort} from '@angular/material/sort';

import {MatTableDataSource} from '@angular/material/table';
import { DonorForBloodType } from 'src/app/shared/models/donor-for-blood-type';
import { DonorForBloodTypeService } from 'src/app/shared/services/donor-for-blood-type.service';


@Component({
  selector: 'app-donor-for-blood-type',
  templateUrl: './donor-for-blood-type.component.html',
  styleUrls: ['./donor-for-blood-type.component.css']
})
export class DonorForBloodTypeComponent implements AfterViewInit {

  donorForBloodTypeList: DonorForBloodType[] = [];
  donorForBloodType: DonorForBloodType = {
    "bloodType": "",
    "quantity": 0
  };

  bloodTypeLabel: string = "Tipo Sanguínio";
  quantityLabel: string = "Quantidade";
  titleLabel: string = "Doadores para cada tipo sanguínio";

  displayedColumns: string[] = [ 'bloodType', 'quantity'];

  dataSource = new MatTableDataSource(this.donorForBloodTypeList);

  constructor(
    private donorForBloodTypeService: DonorForBloodTypeService, 
    private router: Router) {}

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.getDonorsForBloodType();
    this.dataSource.sort = this.sort;
  }

  getDonorsForBloodType() {
    this.donorForBloodTypeService.getDonorsForBloodType().subscribe(
      data => this.donorForBloodTypeList = data
    )
  }
  
  /** Gets the total qauntity of all items */
  getTotalQuantity() {
    return this.donorForBloodTypeList.map(t => t.quantity).reduce((acc, value) => acc + value, 0);
  }

}