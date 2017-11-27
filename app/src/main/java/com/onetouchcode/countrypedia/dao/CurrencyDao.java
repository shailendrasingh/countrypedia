package com.onetouchcode.countrypedia.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.onetouchcode.countrypedia.model.Currency;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@Dao
public interface CurrencyDao {
    @Query("select * from Currency where alpha3Code = :alpha3Code")
    List<Currency> getCurrencyByCountryCode(String alpha3Code);

    @Query("select * from Currency where name = :currencyName")
    List<Currency> getCurrencyByName(String currencyName);

    @Query("select * from Currency")
    List<Currency> getAll();

    @Insert(onConflict = IGNORE)
    void insert(Currency currency);
}
