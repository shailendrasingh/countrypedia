package com.onetouchcode.countrypedia.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.onetouchcode.countrypedia.dto.LanguageDto;

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
public class Language {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String alpha3Code;
    @NonNull
    public String iso639_1;
    @NonNull
    public String iso639_2;
    @NonNull
    public String name;
    @NonNull
    public String nativeName;

    public Language() {
    }

    public Language(@NonNull String CountryCode, LanguageDto languageDto) {
        alpha3Code = CountryCode;
        name = languageDto.name;
        nativeName = languageDto.nativeName;
        iso639_1 = languageDto.iso639_1;
        iso639_2 = languageDto.iso639_2;
    }
}
