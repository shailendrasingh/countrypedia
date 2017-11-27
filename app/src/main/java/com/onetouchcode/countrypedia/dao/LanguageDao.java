package com.onetouchcode.countrypedia.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.onetouchcode.countrypedia.model.Language;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */
@Dao
public interface LanguageDao {
    @Query("select distinct * from Language where alpha3Code = :alpha3Code")
    List<Language> getLanguageByCountryCode(String alpha3Code);

    @Query("select * from Language where name = :languageName")
    List<Language> getLanguageByName(String languageName);

    @Query("select * from Language")
    List<Language> getAll();

    @Insert(onConflict = IGNORE)
    void insert(Language language);
}
