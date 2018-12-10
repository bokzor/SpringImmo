package be.bcdi.immo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.Map;
import java.util.Optional;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImmowebProperty {
  Integer id;
  Integer bedroomCount;
  Optional<Integer> landSurface;
  Optional<Integer> netHabitableSurface;
  ImmowebAddress immowebAddress;
  Integer price;
  Integer monthlyRentalPrice;
  TransactionTypeEnum transactionType;
  PropertyTypeEnum propertyType;
  PebEnum peb;

  @SuppressWarnings("unchecked")
  @JsonProperty("property")
  private void unpackProperty(Map<String, Object> property) {
    this.bedroomCount = (((Map<String, Integer>) property.get("bedroom")).get("count"));
    this.landSurface = Optional.of((((Map<String, Integer>) property.get("land")).get("surface")));
    this.netHabitableSurface = Optional.of((((Map<String, Integer>) property.get("livingDescription")).get("netHabitableSurface")));
    this.propertyType = PropertyTypeEnum.valueOf((String) property.get("type"));
  }
  @SuppressWarnings("unchecked")
  @JsonProperty("transaction")
  private void unpackTransaction(Map<String, Object> transaction) {
    this.peb = PebEnum.valueOf(((Map<String, String>) ((Map<String, Object>) transaction.get("certificates")).get("epc")).get("score"));
    this.transactionType = TransactionTypeEnum.valueOf((String) transaction.get("type"));

    if (this.transactionType == TransactionTypeEnum.FOR_RENT) {
      this.monthlyRentalPrice = ((Map<String, Integer>) transaction.get("rental")).get("monthlyRentalPrice");
    } else if (this.transactionType == TransactionTypeEnum.FOR_SALE) {
      this.price = ((Map<String, Integer>) transaction.get("sale")).get("price");
    }
  }

  enum TransactionTypeEnum {
    FOR_SALE,
    FOR_RENT
  }

  enum PropertyTypeEnum {
    APARTMENT,
    HOUSE
  }

  enum PebEnum {
    A,
    B,
    C,
    D,
    E,
    F,
    G
  }


}
