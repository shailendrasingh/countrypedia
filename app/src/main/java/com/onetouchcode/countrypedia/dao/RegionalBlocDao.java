package com.onetouchcode.countrypedia.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.onetouchcode.countrypedia.model.RegionalBloc;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@Dao
public interface RegionalBlocDao {
    @Query("select * from RegionalBloc where alpha3Code = :alpha3Code")
    List<RegionalBloc> getRegionalBlocByCountryCode(String alpha3Code);

    @Query("select * from RegionalBloc where name = :regionalBlocName")
    List<RegionalBloc> getRegionalBlocByName(String regionalBlocName);

    @Query("select * from RegionalBloc where acronym = :acronym")
    List<RegionalBloc> getRegionalBlocByAcronym(String acronym);

    @Query("select * from RegionalBloc")
    List<RegionalBloc> getAll();

    @Insert(onConflict = IGNORE)
    void insert(RegionalBloc regionalBloc);
}
