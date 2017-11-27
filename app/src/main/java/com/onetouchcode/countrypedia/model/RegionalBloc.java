package com.onetouchcode.countrypedia.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.onetouchcode.countrypedia.dto.RegionalBlocDto;

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
public class RegionalBloc {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String alpha3Code;
    @NonNull
    public String acronym;
    @NonNull
    public String name;

    public RegionalBloc() {
    }

    public RegionalBloc(@NonNull String countryCode, RegionalBlocDto regionalBlocDto) {
        alpha3Code = countryCode;
        acronym = regionalBlocDto.acronym;
        name = regionalBlocDto.name;
    }
}
