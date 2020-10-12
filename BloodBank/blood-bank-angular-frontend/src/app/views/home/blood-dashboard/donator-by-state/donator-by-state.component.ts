import { AfterViewInit, Component, OnInit,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DonatorByState } from 'src/app/shared/models/donator-by-state';
import { DonatorByStateService } from 'src/app/shared/services/donator-by-state.service';


@Component({
  selector: 'app-donator-by-state',
  templateUrl: './donator-by-state.component.html',
  styleUrls: ['./donator-by-state.component.css']
})

export class DonatorByStateComponent implements AfterViewInit {

  donatorByStateList: DonatorByState[] = [];
  donatorByState: DonatorByState = {
    "state": "",
    "quantity": 0
  };

  stateLabel: string = "Estado";
  quantityLabel: string = "Quantidade";
  titleLabel: string = "Doadores por estado";

  displayedColumns: string[] = [ 'state', 'quantity'];

  dataSource = new MatTableDataSource(this.donatorByStateList);

  constructor(
    private donatorByStateService: DonatorByStateService, 
    private router: Router) {}

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.getDonatorsByState();
    this.dataSource.sort = this.sort;
  }

  getDonatorsByState() {
    this.donatorByStateService.getDonatorsByState().subscribe(
      data => this.donatorByStateList = data
    )
  }
  
  /** Gets the total qauntity of all items */
  getTotalQuantity() {
    return this.donatorByStateList.map(t => t.quantity).reduce((acc, value) => acc + value, 0);
  }

}



