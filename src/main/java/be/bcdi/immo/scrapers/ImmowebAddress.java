package be.bcdi.immo.scrapers;

import lombok.Data;

import java.util.Optional;

@Data
public class ImmowebAddress {
  Optional<Double> latitude;
  Optional<Double> longitude;
  String postalCode;
  Optional<String> locality;
  Optional<String> street;
  Optional<String> number;
  Optional<Integer> floor;
  Optional<String> country;

}
