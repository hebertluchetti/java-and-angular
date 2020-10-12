import { AfterViewInit, Component, OnInit,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {MatSort} from '@angular/material/sort';

import {MatTableDataSource} from '@angular/material/table';
import { DonatorForBloodType } from 'src/app/shared/models/donator-for-blood-type';
import { DonatorForBloodTypeService } from 'src/app/shared/services/donator-for-blood-type.service';


@Component({
  selector: 'app-donator-for-blood-type',
  templateUrl: './donator-for-blood-type.component.html',
  styleUrls: ['./donator-for-blood-type.component.css']
})
export class DonatorForBloodTypeComponent implements AfterViewInit {

  donatorForBloodTypeList: DonatorForBloodType[] = [];
  donatorForBloodType: DonatorForBloodType = {
    "bloodType": "",
    "quantity": 0
  };

  bloodTypeLabel: string = "Tipo Sanguínio";
  quantityLabel: string = "Quantidade";
  titleLabel: string = "Doadores para cada tipo sanguínio";

  displayedColumns: string[] = [ 'bloodType', 'quantity'];

  dataSource = new MatTableDataSource(this.donatorForBloodTypeList);

  constructor(
    private donatorForBloodTypeService: DonatorForBloodTypeService, 
    private router: Router) {}

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.getDonatorsForBloodType();
    this.dataSource.sort = this.sort;
  }

  getDonatorsForBloodType() {
    this.donatorForBloodTypeService.getDonatorsForBloodType().subscribe(
      data => this.donatorForBloodTypeList = data
    )
  }
  
  /** Gets the total qauntity of all items */
  getTotalQuantity() {
    return this.donatorForBloodTypeList.map(t => t.quantity).reduce((acc, value) => acc + value, 0);
  }

}