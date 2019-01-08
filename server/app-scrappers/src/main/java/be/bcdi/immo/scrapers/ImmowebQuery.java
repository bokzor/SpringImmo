package be.bcdi.immo.scrapers;

import be.bcdi.immo.model.enums.PropertyTypeEnum;
import lombok.var;

import java.util.ArrayList;
import java.util.HashMap;

public class ImmowebQuery {

    private String country = "BE";
    private ArrayList<String> postalCodes = new ArrayList<String>() {{
        add("4000");
        add("4020");
    }};
    private PropertyTypeEnum propertyType;
    private boolean isALifeAnnuitySale;
    private boolean isNewlyBuilt = false;
    private boolean isSoldOrRented = false;
   private long maxPrice = 150000;
    private long minPrice;
    private Integer rangeMin = 1;
    private Integer rangeMax = 999;
    private Integer maxBedroomCount = 3;
    private Integer minBedroomCount = 2;
    private PriceTypeEnum priceType;
    private ProvinceEnum province;
    private ImmowebProperty.TransactionTypeEnum transactionTypes;

    ImmowebQuery(ImmowebProperty.TransactionTypeEnum transactionTypes, PropertyTypeEnum propertyType) {
        this.setTransactionType(transactionTypes);
        this.setPropertyType(propertyType);
    }

    public ImmowebQuery setMinBedroomCount(Integer minBedroom) {
        this.minBedroomCount = minBedroom;
        return this;
    }

    public ImmowebQuery setProvince(ProvinceEnum province) {
        this.province = province;
        return this;
    }

    public ImmowebQuery setMaxBedroomCount(Integer maxBedroom) {
        this.maxBedroomCount = maxBedroom;
        return this;
    }

    public ImmowebQuery setPropertyType(PropertyTypeEnum propertyTypeEnum) {
        this.propertyType = propertyTypeEnum;
        return this;
    }

    public ImmowebQuery addPostalCode(String postalCode) {
        this.postalCodes.add(postalCode);
        return this;
    }

    public ImmowebQuery setPriceType(PriceTypeEnum priceType) {
        this.priceType = priceType;
        return this;
    }

    public ImmowebQuery setRange(Integer rangeMin, Integer rangeMax) {
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        return this;
    }

    private ImmowebQuery setTransactionType(ImmowebProperty.TransactionTypeEnum transactionType) {
        if (transactionType == ImmowebProperty.TransactionTypeEnum.FOR_RENT) {
            this.priceType = PriceTypeEnum.MONTHLY_RENTAL_PRICE;
        } else {
            this.priceType = PriceTypeEnum.PRICE;
        }

        this.transactionTypes = transactionType;
        return this;
    }

    private String getRange() {
        return this.rangeMin + "-" + this.rangeMax;
    }

    String getQuery() {
        HashMap<String, String> params = new HashMap<>();
        params.put("countries", this.country);
       // params.put("isALifeAnnuitySale", String.valueOf(this.isALifeAnnuitySale));
        //params.put("isNewlyBuilt", String.valueOf(this.isNewlyBuilt));
        params.put("isSoldOrRented", String.valueOf(this.isSoldOrRented));
        params.put("maxPrice", String.valueOf(this.maxPrice));
        if(this.province == null) {
            params.put("postalCodes", String.join(",", this.postalCodes));
        } else {
            params.put("provinces",this.province.name());
        }
        params.put("priceType", this.priceType.name());
        params.put("propertyTypes", this.propertyType.name());
        params.put("range", this.getRange());
        params.put("transactionTypes", this.transactionTypes.name());
        params.put("maxBedroomCount", String.valueOf(this.maxBedroomCount));
        params.put("minBedroomCount", String.valueOf(this.minBedroomCount));
        return "?" + this.paramsGenerator(params);
    }

    private String paramsGenerator(HashMap<String, String> params) {
        StringBuilder appendedParams = new StringBuilder();
        for (var setValue : params.entrySet()) {
            if (setValue.getValue() != null && !setValue.getValue().isEmpty()) {
                appendedParams.append(setValue.getKey()).append("=").append(setValue.getValue()).append("&");
            }
        }
        return appendedParams.toString();
    }


    enum PriceTypeEnum {
        PRICE,
        MONTHLY_RENTAL_PRICE
    }

    enum ProvinceEnum {
        LIEGE
    }


}
