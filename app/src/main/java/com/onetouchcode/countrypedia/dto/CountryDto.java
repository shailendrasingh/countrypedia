package com.onetouchcode.countrypedia.dto;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class CountryDto {
    public String name;
    public String nativeName;
    public String demonym;
    public String alpha2Code;
    public String alpha3Code;
    public String capital;
    public String region;
    @JsonField(name = "subregion")
    public String subRegion;
    public long population;
    public Double area;
    public Double gini;
    public String numericCode;
    public String flag;
    public String cioc;
    public String[] callingCodes;
    public String[] latlng;
    public String[] topLevelDomain;
    public String[] altSpellings;
    public String[] timezones;
    public String[] borders;
    public HashMap<String, String> translations;
    public List<CurrencyDto> currencies;
    public List<LanguageDto> languages;
    public List<RegionalBlocDto> regionalBlocs;

    @Override
    public String toString() {
        return "CountryDto{" +
                "name='" + name + '\'' +
                ", nativeName='" + nativeName + '\'' +
                ", demonym='" + demonym + '\'' +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", subRegion='" + subRegion + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", gini=" + gini +
                ", numericCode='" + numericCode + '\'' +
                ", flag='" + flag + '\'' +
                ", cioc='" + cioc + '\'' +
                ", callingCodes=" + Arrays.toString(callingCodes) +
                ", latlng=" + Arrays.toString(latlng) +
                ", topLevelDomain=" + Arrays.toString(topLevelDomain) +
                ", altSpellings=" + Arrays.toString(altSpellings) +
                ", timezones=" + Arrays.toString(timezones) +
                ", borders=" + Arrays.toString(borders) +
                ", translations=" + translations +
                ", currencies=" + currencies +
                ", languages=" + languages +
                ", regionalBlocs=" + regionalBlocs +
                '}';
    }
}
