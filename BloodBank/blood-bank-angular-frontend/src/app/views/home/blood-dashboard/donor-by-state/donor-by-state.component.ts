import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DonorByState } from 'src/app/shared/models/donor-by-state';
import { DonorByStateService } from 'src/app/shared/services/donor-by-state.service';


@Component({
  selector: 'app-donor-by-state',
  templateUrl: './donor-by-state.component.html',
  styleUrls: ['./donor-by-state.component.css']
})

export class DonorByStateComponent implements AfterViewInit {

  donorByStateList: DonorByState[] = [];
  donorByState: DonorByState = {
    "state": "",
    "quantity": 0
  };

  stateLabel: string = "Estado";
  quantityLabel: string = "Quantidade";
  titleLabel: string = "Doadores por estado";

  displayedColumns: string[] = [ 'state', 'quantity'];

  dataSource = new MatTableDataSource(this.donorByStateList);

  constructor(
    private donorByStateService: DonorByStateService, 
    private router: Router) {}

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.getDonorsByState();
    this.dataSource.sort = this.sort;
  }

  getDonorsByState() {
    this.donorByStateService.getDonorsByState().subscribe(
      data => this.donorByStateList = data
    )
  }
  
  /** Gets the total qauntity of all items */
  getTotalQuantity() {
    return this.donorByStateList.map(t => t.quantity).reduce((acc, value) => acc + value, 0);
  }

}



