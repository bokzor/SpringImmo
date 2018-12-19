import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StatTableComponent} from "./stat/stat-table/stat-table.component";
import {StatWrapperComponent} from "./stat/stat-wrapper/stat-wrapper.component";

const routes: Routes = [
  {
    path: '', component: StatWrapperComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
