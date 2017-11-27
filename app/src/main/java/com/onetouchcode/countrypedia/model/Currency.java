package com.onetouchcode.countrypedia.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.onetouchcode.countrypedia.dto.CurrencyDto;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@Entity(indices = {@Index(value = {"alpha3Code", "name"}, unique = true)},
        foreignKeys = {
        @ForeignKey(entity = Country.class,
                parentColumns = "alpha3Code",
                childColumns = "alpha3Code")
}
)
public class Currency {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String name;
    @NonNull
    public String symbol;
    @NonNull
    public String alpha3Code;

    public Currency(){}
    public Currency(@NonNull String countryCode,CurrencyDto currencyDto){
        name = currencyDto.name;
        symbol = currencyDto.symbol;
        alpha3Code = countryCode;
    }
}
