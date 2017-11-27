/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onetouchcode.countrypedia.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Environment;

import com.onetouchcode.countrypedia.BuildConfig;
import com.onetouchcode.countrypedia.dao.CountryDao;
import com.onetouchcode.countrypedia.dao.CurrencyDao;
import com.onetouchcode.countrypedia.dao.LanguageDao;
import com.onetouchcode.countrypedia.dao.RegionalBlocDao;
import com.onetouchcode.countrypedia.model.Country;
import com.onetouchcode.countrypedia.model.Currency;
import com.onetouchcode.countrypedia.model.Language;
import com.onetouchcode.countrypedia.model.RegionalBloc;

import java.io.File;

@Database(entities = {Country.class, Currency.class, Language.class, RegionalBloc.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    private static String DATABASE_NAME = "countries.db";
    public abstract CountryDao countryModel();

    public abstract CurrencyDao currencyModel();

    public abstract LanguageDao languageModel();

    public abstract RegionalBlocDao regionalBlocModel();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}