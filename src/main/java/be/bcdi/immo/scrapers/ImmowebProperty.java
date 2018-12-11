package be.bcdi.immo.scrapers;

import be.bcdi.immo.enums.PebEnum;
import be.bcdi.immo.enums.PropertyTypeEnum;
import be.bcdi.immo.enums.SourceEnum;
import be.bcdi.immo.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
import java.util.Optional;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImmowebProperty {
    Integer id;
    Integer bedroomCount;
    Integer landSurface;
    Integer netHabitableSurface;
    ImmowebAddress immowebAddress;
    Integer price;
    Integer monthlyRentalPrice;
    TransactionTypeEnum transactionType;
    PropertyTypeEnum propertyType;
    PebEnum peb = null;
    SourceEnum source = SourceEnum.IMMOWEB;

    ImmowebProperty() {
        this.immowebAddress = new ImmowebAddress();
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("property")
    private void unpackProperty(Map<String, Object> property) throws InstantiationException, IllegalAccessException {
        this.bedroomCount = JsonUtils.get(property, "bedroom.count", Integer.class).orElse(0);
        this.landSurface = JsonUtils.get(property, "land.surface", Integer.class).orElse(0);
        this.netHabitableSurface = JsonUtils.get(property, "livingDescription.netHabitableSurface", Integer.class).orElse(0);
        this.propertyType = PropertyTypeEnum.valueOf((String) property.get("type"));

        this.immowebAddress.latitude = JsonUtils.get(property, "location.geoPoint.latitude", Double.class);
        this.immowebAddress.longitude = JsonUtils.get(property, "location.geoPoint.longitude", Double.class);
        this.immowebAddress.street = JsonUtils.get(property, "location.address.street", String.class);
        this.immowebAddress.postalCode = JsonUtils.get(property, "location.address.postalCode", String.class).get();
        this.immowebAddress.number = JsonUtils.get(property, "location.address.number", String.class);
        this.immowebAddress.locality = JsonUtils.get(property, "location.address.locality", String.class);
        this.immowebAddress.country = JsonUtils.get(property, "location.address.country", String.class);
        this.immowebAddress.floor = JsonUtils.get(property, "location.address.floor", Integer.class);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("transaction")
    private void unpackTransaction(Map<String, Object> transaction) throws InstantiationException, IllegalAccessException {
        this.peb = JsonUtils.get(transaction, "certificates.epc.score", PebEnum.class).orElse(null);
        this.transactionType = TransactionTypeEnum.valueOf((String) transaction.get("type"));

        if (this.transactionType == TransactionTypeEnum.FOR_RENT) {
            this.monthlyRentalPrice = JsonUtils.get(transaction, "rental.monthlyRentalPrice", Integer.class).get();
            this.price = null;
        } else if (this.transactionType == TransactionTypeEnum.FOR_SALE) {
            this.price = JsonUtils.get(transaction, "sale.price", Integer.class).get();
            this.monthlyRentalPrice = null;
        }
    }


    public enum TransactionTypeEnum {
        FOR_SALE,
        FOR_RENT
    }


}
