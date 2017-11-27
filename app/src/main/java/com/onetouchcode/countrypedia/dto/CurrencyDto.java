package com.onetouchcode.countrypedia.dto;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class CurrencyDto {
    @JsonField(name = "code")
    public String alpha3Code;
    public String name;
    public String symbol;

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "alpha3Code='" + alpha3Code + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
