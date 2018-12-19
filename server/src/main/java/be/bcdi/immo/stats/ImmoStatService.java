package be.bcdi.immo.stats;

import be.bcdi.immo.domain.ImmoStat;
import be.bcdi.immo.domain.ValueByPostalCode;
import be.bcdi.immo.entities.ImmoProperty;
import be.bcdi.immo.repositories.ImmoPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class ImmoStatService {

    private ImmoPropertyRepository immoPropertyRepository;

    ImmoStatService(ImmoPropertyRepository immoPropertyRepository) {
        this.immoPropertyRepository = immoPropertyRepository;
    }

    public ImmoStat getStat() {
        ImmoStat immoStat =  new ImmoStat();

        immoStat.setTwoRoomsToRent(getCountToRent(2));
        immoStat.setTwoRoomsToSale(this.getCountToSale(2));
        immoStat.setTwoRoomsAvgSellingPrice(this.getAvgSellingPrice(2));
        immoStat.setTwoRoomsAvgRentingPrice(getAvgRentingPrice (2));

        immoStat.setThreeRoomsToRent(getCountToRent(3));
        immoStat.setThreeRoomsToSale(this.getCountToSale(3));
        immoStat.setThreeRoomsAvgSellingPrice(this.getAvgSellingPrice(3));
        immoStat.setThreeRoomsAvgRentingPrice(getAvgRentingPrice (3));


        immoStat.setAvgSellingPriceBySurface(getAvgSellingPriceBySurface());
        immoStat.setAvgRentingPriceBySurface(getAvgRentingPriceBySurface());


        return immoStat;
    }

    List<ValueByPostalCode> getAvgSellingPrice(Integer bedroomCount) {
        return this.immoPropertyRepository.querySellingPropertyByBedroom(bedroomCount)
                .stream().collect(
                        groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()), averagingDouble(ImmoProperty::getSellingPrice))
                ).entrySet().stream().map(ValueByPostalCode::new).collect(Collectors.toList());
    }

    List<ValueByPostalCode> getAvgRentingPrice(Integer bedroomCount) {
       return  this.immoPropertyRepository.queryRentingPropertyByBedroom(bedroomCount)
                .stream().collect(
                groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()), averagingDouble(ImmoProperty::getRentingPrice))
        ).entrySet().stream().map(ValueByPostalCode::new).collect(Collectors.toList());
    }

    List<ValueByPostalCode> getCountToRent(Integer bedroomCount) {
        return this.immoPropertyRepository.queryRentingPropertyByBedroom(bedroomCount)
                .stream().collect(
                groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()), counting())
        ).entrySet().stream().map(ValueByPostalCode::newLong).collect(Collectors.toList());
    }

    List<ValueByPostalCode> getCountToSale(Integer bedroomCount) {
        return this.immoPropertyRepository.querySellingPropertyByBedroom(bedroomCount)
                .stream().collect(
                groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()), counting())
        ).entrySet().stream().filter((e -> e.getValue() > 5)).map(ValueByPostalCode::newLong).collect(Collectors.toList());
    }

    List<ValueByPostalCode> getAvgSellingPriceBySurface() {
        return this.immoPropertyRepository.querySellingPropertyWithSurfaceIndicated()
                .stream().collect(
                        groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()),
                                averagingDouble((immoProperty -> immoProperty.getSellingPrice() / immoProperty.getNetHabitableSurface()))
                        )
                ).entrySet().stream().map(ValueByPostalCode::new).collect(Collectors.toList());
    }

    List<ValueByPostalCode> getAvgRentingPriceBySurface() {
        return this.immoPropertyRepository.queryRentingPropertyWithSurfaceIndicated()
                .stream().collect(
                        groupingBy((immoProperty -> immoProperty.getAddress().getPostalCode()),
                                averagingDouble((immoProperty -> immoProperty.getRentingPrice() / immoProperty.getNetHabitableSurface()))
                        )
                ).entrySet().stream().map(ValueByPostalCode::new).collect(Collectors.toList());
    }



}
