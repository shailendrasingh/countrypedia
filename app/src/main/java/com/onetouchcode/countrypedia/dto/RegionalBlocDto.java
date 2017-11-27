package com.onetouchcode.countrypedia.dto;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class RegionalBlocDto {
    public String acronym;
    public String name;

    @Override
    public String toString() {
        return "RegionalBlocDto{" +
                "acronym='" + acronym + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
