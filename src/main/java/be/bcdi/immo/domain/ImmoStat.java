package be.bcdi.immo.domain;


import lombok.Data;

import java.util.List;

@Data
public class ImmoStat {

    List<ValueByPostalCode> twoRoomsToSale;
    List<ValueByPostalCode> twoRoomsToRent;

    List<ValueByPostalCode> threeRoomsToRent;
    List<ValueByPostalCode> threeRoomsToSale;

    List<ValueByPostalCode> thwoRoomsAvgSellingPrice;
    List<ValueByPostalCode> thwoRoomsAvgRentingPrice;

    List<ValueByPostalCode> threeRoomsAvgRentingPrice;
    List<ValueByPostalCode> threeRoomsAvgSellingPrice;


    List<ValueByPostalCode> AvgSellingPriceBySurface;
    List<ValueByPostalCode> AvgRentingPriceBySurface;


}
