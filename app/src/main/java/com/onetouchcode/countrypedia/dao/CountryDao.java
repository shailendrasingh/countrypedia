package com.onetouchcode.countrypedia.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.onetouchcode.countrypedia.model.Country;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@Dao
public interface CountryDao {
    @Query("select * from Country where alpha3Code = :alpha3Code")
    Country getCountryByCode(String alpha3Code);

    @Query("select * from Country where name = :countryName")
    Country getCountryByName(String countryName);

    @Query("select * from Country order by name asc")
    List<Country> getAll();

    @Insert(onConflict = REPLACE)
    void insert(Country country);

}
