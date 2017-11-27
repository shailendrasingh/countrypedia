package com.onetouchcode.countrypedia.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.onetouchcode.countrypedia.dto.CountryDto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@Entity
public class Country implements Serializable{
    @PrimaryKey
    @NonNull
    public String alpha3Code;
    public String name;
    public String nativeName;
    public String demonym;
    public String alpha2Code;
    public String capital;
    public String region;
    public String subRegion;
    public long population;
    public Double area;
    public Double gini;
    public String numericCode;
    public String flag;
    public String cioc;
    public String callingCodes;
    public String latlng;
    public String topLevelDomain;
    public String altSpellings;
    public String timezones;
    public String borders;
    public String translations;

    public Country(){}
    public Country(CountryDto countryDto) {
        alpha3Code = countryDto.alpha3Code;
        name = countryDto.name;
        nativeName = countryDto.nativeName;
        demonym = countryDto.demonym;
        alpha2Code = countryDto.alpha2Code;
        capital = countryDto.capital;
        region = countryDto.region;
        subRegion = countryDto.subRegion;
        population = countryDto.population;
        area = countryDto.area;
        gini = countryDto.gini;
        numericCode = countryDto.numericCode;
        flag = countryDto.flag;
        cioc = countryDto.cioc;
        callingCodes = Arrays.toString(countryDto.callingCodes);
        latlng = Arrays.toString(countryDto.latlng);
        topLevelDomain = Arrays.toString(countryDto.topLevelDomain);
        altSpellings = Arrays.toString(countryDto.altSpellings);
        timezones = Arrays.toString(countryDto.timezones);
        borders = Arrays.toString(countryDto.borders);
        translations = countryDto.translations.toString();
    }
}
