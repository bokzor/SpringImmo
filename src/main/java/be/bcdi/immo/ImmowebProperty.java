package be.bcdi.immo;

import be.bcdi.immo.utils.JsonUtils;
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
  Optional<Integer> bedroomCount;
  Optional<Integer> landSurface;
  Optional<Integer> netHabitableSurface;
  ImmowebAddress immowebAddress;
  Optional<Integer> price;
  Optional<Integer> monthlyRentalPrice;
  TransactionTypeEnum transactionType;
  PropertyTypeEnum propertyType;
  Optional<PebEnum> peb;

  @SuppressWarnings("unchecked")
  @JsonProperty("property")
  private void unpackProperty(Map<String, Object> property) throws InstantiationException, IllegalAccessException {
    this.bedroomCount = JsonUtils.get(property, "bedroom.count", Integer.class);
    this.landSurface = JsonUtils.get(property, "land.surface", Integer.class);
    this.netHabitableSurface = JsonUtils.get(property,"livingDescription.netHabitableSurface", Integer.class);
    this.propertyType = PropertyTypeEnum.valueOf((String) property.get("type"));
  }
  @SuppressWarnings("unchecked")
  @JsonProperty("transaction")
  private void unpackTransaction(Map<String, Object> transaction) throws InstantiationException, IllegalAccessException {
    this.peb = JsonUtils.get(transaction, "certificates.epc.score", PebEnum.class);
    this.transactionType = TransactionTypeEnum.valueOf((String) transaction.get("type"));

    if (this.transactionType == TransactionTypeEnum.FOR_RENT) {
      this.monthlyRentalPrice = JsonUtils.get(transaction, "rental.monthlyRentalPrice", Integer.class);
    } else if (this.transactionType == TransactionTypeEnum.FOR_SALE) {
      this.price = JsonUtils.get(transaction, "sale.price", Integer.class);
    }
  }

  public enum TransactionTypeEnum {
    FOR_SALE,
    FOR_RENT
  }

  public enum PropertyTypeEnum {
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
