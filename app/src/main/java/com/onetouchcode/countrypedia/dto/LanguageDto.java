package com.onetouchcode.countrypedia.dto;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class LanguageDto {

    public String iso639_1;
    public String iso639_2;
    public String name;
    public String nativeName;

    @Override
    public String toString() {
        return "LanguageDto{" +
                "iso639_1='" + iso639_1 + '\'' +
                ", iso639_2='" + iso639_2 + '\'' +
                ", name='" + name + '\'' +
                ", nativeName='" + nativeName + '\'' +
                '}';
    }
}
