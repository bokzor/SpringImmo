import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {StatTableComponent} from "./stat-table/stat-table.component";
import {
  MatCheckboxModule,
  MatInputModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSortModule,
  MatTableModule
} from "@angular/material";
import {HttpClientModule} from "@angular/common/http";
import { StatWrapperComponent } from './stat-wrapper/stat-wrapper.component';

@NgModule({
  declarations: [
    StatTableComponent,
    StatWrapperComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    MatTableModule,
    MatSortModule,
    MatInputModule,
    MatCheckboxModule,
    MatPaginatorModule,
    MatProgressSpinnerModule
  ],
  exports: [
    StatTableComponent,
  ]
})
export class StatModule { }
