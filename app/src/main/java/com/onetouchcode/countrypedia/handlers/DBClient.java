package com.onetouchcode.countrypedia.handlers;

import android.content.Context;

import com.onetouchcode.countrypedia.Logger;
import com.onetouchcode.countrypedia.db.AppDatabase;
import com.onetouchcode.countrypedia.dto.CountryDto;
import com.onetouchcode.countrypedia.dto.CurrencyDto;
import com.onetouchcode.countrypedia.dto.LanguageDto;
import com.onetouchcode.countrypedia.dto.RegionalBlocDto;
import com.onetouchcode.countrypedia.model.Country;
import com.onetouchcode.countrypedia.model.Currency;
import com.onetouchcode.countrypedia.model.Language;
import com.onetouchcode.countrypedia.model.RegionalBloc;

import java.util.List;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

public class DBClient {
    private static final String TAG = DBClient.class.getSimpleName();
    private Context context;
    private AppDatabase db;

    public DBClient(Context context) {
        this.context = context;
        db = AppDatabase.getInMemoryDatabase(context);
    }


    public List<Country> getCountryList() {
        return db.countryModel().getAll();
    }

    public List<Language> getLanguages(String countryCode) {
        return db.languageModel().getLanguageByCountryCode(countryCode);
    }

    public List<Currency> getCurrencies(String countryCode) {
        return db.currencyModel().getCurrencyByCountryCode(countryCode);
    }

    public List<RegionalBloc> getRegionalBlocs(String countryCode) {
        return db.regionalBlocModel().getRegionalBlocByCountryCode(countryCode);
    }


    public boolean insertCountries(List<CountryDto> countryDtoList) {
        for (CountryDto countryDto : countryDtoList) {
            Logger.logInfo(TAG, countryDto.toString());
            db.countryModel().insert(new Country(countryDto));
            insertCurrency(countryDto);
            insertLanguage(countryDto);
            insertRegionalBloc(countryDto);

        }
        return true;
    }

    private boolean insertCurrency(CountryDto countryDto) {
        for (CurrencyDto currencyDto : countryDto.currencies) {
            db.currencyModel().insert(new Currency(countryDto.alpha3Code, currencyDto));
        }
        return true;
    }

    private boolean insertLanguage(CountryDto countryDto) {
        for (LanguageDto languageDto : countryDto.languages) {
            db.languageModel().insert(new Language(countryDto.alpha3Code, languageDto));
        }
        return true;
    }

    private boolean insertRegionalBloc(CountryDto countryDto) {
        for (RegionalBlocDto regionalBlocDto : countryDto.regionalBlocs) {
            db.regionalBlocModel().insert(new RegionalBloc(countryDto.alpha3Code, regionalBlocDto));
        }
        return true;
    }

}
