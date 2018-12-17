package be.bcdi.immo.stats;

import be.bcdi.immo.domain.ImmoStat;
import be.bcdi.immo.domain.ValueByPostalCode;
import be.bcdi.immo.entities.ImmoProperty;
import be.bcdi.immo.repositories.ImmoPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class ImmoStatService {

    private ImmoPropertyRepository immoPropertyRepository;

    ImmoStatService(ImmoPropertyRepository immoPropertyRepository) {
        this.immoPropertyRepository = immoPropertyRepository;
    }

    public ImmoStat getStat() {
        ImmoStat immoStat =  new ImmoStat();

        List<ValueByPostalCode> twoRoomsToRent = this.immoPropertyRepository.queryRentingPropertyByBedroom(2)
                .stream().collect(
                groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()), counting())
        ).entrySet().stream().map(ValueByPostalCode::newLong).collect(Collectors.toList());
        immoStat.setTwoRoomsToRent(twoRoomsToRent);

        List<ValueByPostalCode> twoRoomsToSale = this.immoPropertyRepository.querySellingPropertyByBedroom(2)
                .stream().collect(
                        groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()), counting())
                ).entrySet().stream().map(ValueByPostalCode::newLong).collect(Collectors.toList());
        immoStat.setTwoRoomsToSale(twoRoomsToSale);

        List<ValueByPostalCode> thwoRoomsAvgSellingPrice = this.immoPropertyRepository.querySellingPropertyByBedroom(2)
                .stream().collect(
                        groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()), averagingDouble(ImmoProperty::getSellingPrice))
                ).entrySet().stream().map(ValueByPostalCode::new).collect(Collectors.toList());
        immoStat.setAvgSellingPriceBySurface(thwoRoomsAvgSellingPrice);

        List<ValueByPostalCode> thwoRoomsAvgRentingPrice = this.immoPropertyRepository.queryRentingPropertyByBedroom(2)
                .stream().collect(
                        groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()), averagingDouble(ImmoProperty::getRentingPrice))
                ).entrySet().stream().map(ValueByPostalCode::new).collect(Collectors.toList());
        immoStat.setThreeRoomsAvgRentingPrice(thwoRoomsAvgRentingPrice);


        return immoStat;
    }


}
