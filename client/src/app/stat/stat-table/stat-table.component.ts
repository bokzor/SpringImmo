import {Component, Input, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {StatColumn} from "./stat-column.interface";


@Component({
  selector: 'stat-table',
  styleUrls: ['stat-table.component.scss'],
  templateUrl: 'stat-table.component.html',
})
export class StatTableComponent implements OnInit {

  @Input()
  set postalCodes(data: string[]) {
    this.dataSource = new MatTableDataSource(data);
  }

  dataSource: MatTableDataSource<string>;

  @Input()
  displayedColumns: StatColumn[] = [];

  @Input()
  valueGetter: (string, string) => number;

  ngOnInit() {
    this.dataSource = new MatTableDataSource(this._postalCodes);
  }


  getColumnsName(): string[] {
    return this.displayedColumns.map((col) => col.key);
  }

  private _postalCodes: string[] = [];


}

