package be.bcdi.immo.model.domain;


import lombok.Data;

import java.util.List;

@Data
public class ImmoStat {

    List<ValueByPostalCode> twoRoomsToSale;
    List<ValueByPostalCode> twoRoomsToRent;

    List<ValueByPostalCode> threeRoomsToRent;
    List<ValueByPostalCode> threeRoomsToSale;

    List<ValueByPostalCode> twoRoomsAvgSellingPrice;
    List<ValueByPostalCode> twoRoomsAvgRentingPrice;

    List<ValueByPostalCode> threeRoomsAvgRentingPrice;
    List<ValueByPostalCode> threeRoomsAvgSellingPrice;

    List<ValueByPostalCode> avgSellingPriceBySurface;
    List<ValueByPostalCode> avgRentingPriceBySurface;


}
