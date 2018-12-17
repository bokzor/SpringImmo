package be.bcdi.immo.domain;

import lombok.Data;

import java.util.Map;

@Data
public class ValueByPostalCode {
    Double value;
    String postalCode;


    public ValueByPostalCode(Map.Entry<String, Double> entry) {
        this.postalCode = entry.getKey();
        this.value = entry.getValue();
    }

    public ValueByPostalCode(String postalCode, Double value) {
        this.postalCode = postalCode;
        this.value = value;
    }

    public static ValueByPostalCode newLong(Map.Entry<String, Long> entry) {
       return new ValueByPostalCode(entry.getKey(), entry.getValue().doubleValue());

    }

}
