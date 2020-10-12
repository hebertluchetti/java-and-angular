import { AfterViewInit, Component,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatSort } from '@angular/material/sort';

import { MatTableDataSource } from '@angular/material/table';
import { ImcAvgByAgeRange } from 'src/app/shared/models/imc-avg-by-age-range';
import { ImcAvgByAgeRangeService } from 'src/app/shared/services/imc-avg-by-age-range.service';

@Component({
  selector: 'app-imc-avg-by-age-range',
  templateUrl: './imc-avg-by-age-range.component.html',
  styleUrls: ['./imc-avg-by-age-range.component.css']
})
export class ImcAvgByAgeRangeComponent implements AfterViewInit {

  imcAvgByAgeRangeList: ImcAvgByAgeRange[] = [];
  imcAvgByAgeRange: ImcAvgByAgeRange = {
    "ageRange": "",
    "imcAvg": 0
  };

  ageRangeLabel: string = "Faixa de Idade";
  imcAvgLabel: string = "Média de IMC";
  titleLabel: string = "Média de IMC por faixa de idade";

  displayedColumns: string[] = [ 'ageRange', 'imcAvg'];
  dataSource = new MatTableDataSource(this.imcAvgByAgeRangeList);

  constructor(
    private imcAvgByAgeRangeService: ImcAvgByAgeRangeService, 
    private router: Router) {}

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.getImcAvgByAgeRanges();
    this.dataSource.sort = this.sort;
  }

  getImcAvgByAgeRanges() {
    this.imcAvgByAgeRangeService.getImcAvgByAgeRanges().subscribe(
      data => this.imcAvgByAgeRangeList = data
    )
  }
}
