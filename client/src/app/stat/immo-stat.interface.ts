export interface ImmoStatInterface {
  twoRoomsToSale: ValueByPostalCode[];
  twoRoomsToRent: ValueByPostalCode[];
  threeRoomsToRent: ValueByPostalCode[];
  threeRoomsToSale: ValueByPostalCode[];

  twoRoomsAvgSellingPrice: ValueByPostalCode[];
  twoRoomsAvgRentingPrice: ValueByPostalCode[];

  threeRoomsAvgSellingPrice: ValueByPostalCode[];
  threeRoomsAvgRentingPrice: ValueByPostalCode[];


  avgSellingPriceBySurface: ValueByPostalCode[];
  avgRentingPriceBySurface: ValueByPostalCode[];

}

export interface ValueByPostalCode {
  value: number,
  postalCode: String
}
