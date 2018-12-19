import {Component, OnInit} from '@angular/core';
import {StatService} from "../stat.service";
import {ImmoStatInterface, ValueByPostalCode} from "../immo-stat.interface";
import {StatColumn} from "../stat-table/stat-column.interface";

@Component({
  selector: 'app-stat-wrapper',
  templateUrl: './stat-wrapper.component.html',
  styleUrls: ['./stat-wrapper.component.scss']
})
export class StatWrapperComponent implements OnInit {

  stat: ImmoStatInterface;
  postalCodes: string[];
  displayedColumns: StatColumn[];

  constructor(private statService: StatService) {
  }

  ngOnInit() {
    this.displayedColumns =
      [
        {key: 'postalCode', label: 'Code Postal'},
        {key: 'twoRoomsToSale', label: '2 ch A Vendre'},
        {key: 'twoRoomsToRent', label: '2 ch a Louer'},
        {key: 'threeRoomsToSale', label: '3 ch a Vendre'},
        {key: 'threeRoomsToRent', label: '3 ch a Louer'},
        {key: 'twoRoomsAvgSellingPrice', label: '2ch prix vente moye'},
        {key: 'threeRoomsAvgSellingPrice', label: '3ch prix vente moyen'},
        {key: 'twoRoomsAvgRentingPrice', label: '2ch prix location moyen'},
        {key: 'threeRoomsAvgRentingPrice', label: '3ch prix location moyen'},
        {key: 'avgSellingPriceBySurface', label: 'ratio 2 ch'},
        {key: 'avgRentingPriceBySurface', label: 'ratio 3 ch'},
        {key: 'ratioTwoRooms', label: 'Prix vente m2'},
        {key: 'ratioThreeRooms', label: 'Prix location m2'},
      ];

    this.statService.getStats().subscribe((stat) => {
      this.stat = stat;
      this.postalCodes = this.getPostalCodes(stat);
    })
  }

  getPostalCodes(stat: ImmoStatInterface) {
    return stat.twoRoomsToSale.map((value => value.postalCode));
  }

  valueGetter = (postalCode, key) => {
    switch (key) {
      case "postalCode":
        return postalCode;
      case "ratioTwoRooms":
        return this.getRatioTwoRooms(postalCode);
      case 'ratioThreeRooms':
        return this.getRatioThreeRooms(postalCode);
      default:
        if (!this.stat[key]) {
          return;
        }
        const val: ValueByPostalCode = this.stat[key].find((valueByPostalCode: ValueByPostalCode) => valueByPostalCode.postalCode == postalCode);
        return val ? Math.floor(val.value) : 0;
    }
  };


  getRatioTwoRooms(postalCode) {
    const twoRoomsAvgSellingPrice: ValueByPostalCode = this.stat.twoRoomsAvgSellingPrice.find((valueByPostalCode: ValueByPostalCode) => valueByPostalCode.postalCode == postalCode);
    const twoRoomsAvgRentingPrice: ValueByPostalCode = this.stat.twoRoomsAvgRentingPrice.find((valueByPostalCode: ValueByPostalCode) => valueByPostalCode.postalCode == postalCode);

    if (twoRoomsAvgSellingPrice && twoRoomsAvgRentingPrice) {
      if (twoRoomsAvgSellingPrice.value > 0 && twoRoomsAvgRentingPrice.value > 0) {
        return ((twoRoomsAvgRentingPrice.value / twoRoomsAvgSellingPrice.value) * 100).toFixed(2);
      }
    }
    return "0"
  }

  getRatioThreeRooms(postalCode) {
    const threeRoomsAvgSellingPrice: ValueByPostalCode = this.stat.threeRoomsAvgSellingPrice.find((valueByPostalCode: ValueByPostalCode) => valueByPostalCode.postalCode == postalCode);
    const threeRoomsAvgRentingPrice: ValueByPostalCode = this.stat.threeRoomsAvgRentingPrice.find((valueByPostalCode: ValueByPostalCode) => valueByPostalCode.postalCode == postalCode);


    if (threeRoomsAvgSellingPrice && threeRoomsAvgRentingPrice) {
      if (threeRoomsAvgSellingPrice.value > 0 && threeRoomsAvgRentingPrice.value > 0) {
        return ((threeRoomsAvgRentingPrice.value / threeRoomsAvgSellingPrice.value) * 100).toFixed(2);
      }
    }
    return "0"
  }

}
