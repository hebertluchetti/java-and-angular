import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './views/home/home.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { BloodDashboardComponent } from './views/home/blood-dashboard/blood-dashboard.component';
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableModule} from '@angular/material/table';
import { DonorByStateComponent } from './views/home/blood-dashboard/donor-by-state/donor-by-state.component';
import { AgeAvgByBloodTypeComponent } from './views/home/blood-dashboard/age-avg-by-blood-type/age-avg-by-blood-type.component';
import { ImcAvgByAgeRangeComponent } from './views/home/blood-dashboard/imc-avg-by-age-range/imc-avg-by-age-range.component';
import { ObesityByGenderComponent } from './views/home/blood-dashboard/obesity-by-gender/obesity-by-gender.component';
import { DonorForBloodTypeComponent } from './views/home/blood-dashboard/donor-for-blood-type/donor-for-blood-type.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BloodDashboardComponent,
    DonorByStateComponent,
    AgeAvgByBloodTypeComponent,
    ImcAvgByAgeRangeComponent,
    ObesityByGenderComponent,
    DonorForBloodTypeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatTabsModule,
    MatTableModule,
    HttpClientModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
