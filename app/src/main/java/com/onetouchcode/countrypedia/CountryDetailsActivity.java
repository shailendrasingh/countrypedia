package com.onetouchcode.countrypedia;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onetouchcode.countrypedia.handlers.DBClient;
import com.onetouchcode.countrypedia.model.Country;
import com.onetouchcode.countrypedia.model.Currency;
import com.onetouchcode.countrypedia.model.Language;
import com.onetouchcode.countrypedia.model.RegionalBloc;
import com.onetouchcode.countrypedia.svgutil.GlideApp;
import com.onetouchcode.countrypedia.svgutil.SvgSoftwareLayerSetter;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by CropIn-Shailendra on 11/25/2017.
 */

public class CountryDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DBClient dbClient;
    private Country country;
    private TextView tvCountryName, tvCapital, tvTimezones, tvLanguage, tvCurrency, tvRegionalBloc;
    private LinearLayout llLanguage, llCurrency, llRegionalBloc;
    private ImageView ivFlag;
    private SupportMapFragment mapFragment;
    private RequestBuilder<PictureDrawable> requestBuilder = null;
    private List<Language> languageList;
    private List<Currency> currencyList;
    private List<RegionalBloc> regionalBlocList;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        fetchExtras();
        init();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews();
        initMap();
        renderViews();
    }

    private void fetchExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            country = (Country) extras.getSerializable("country");
        }
    }

    private void init() {
        dbClient = new DBClient(this);
        requestBuilder = GlideApp.with(this)
                .as(PictureDrawable.class)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());
    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.details);
        llLanguage = findViewById(R.id.llLanguage);
        llCurrency = findViewById(R.id.llCurrency);
        llRegionalBloc = findViewById(R.id.llRegionalBloc);
        tvCountryName = findViewById(R.id.tvCountryName);
        tvCapital = findViewById(R.id.tvCapitalName);
        ivFlag = findViewById(R.id.ivFlag);
        tvTimezones = findViewById(R.id.tvTimezones);
        tvLanguage = findViewById(R.id.tvLanguage);
        tvCurrency = findViewById(R.id.tvCurrency);
        tvRegionalBloc = findViewById(R.id.tvRegionalBloc);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
    }

    private void initMap() {
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final float MAX_ZOOMING_VALUE = 5f;
        String strLatLng = country.latlng.replace("[", "");
        strLatLng = strLatLng.replace("]", "");
        String array[] = strLatLng.split(",");
        LatLng latLng = new LatLng(Double.parseDouble(array[0].trim()), Double.parseDouble(array[1]));
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(Boolean.TRUE);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(latLng)).setVisible(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MAX_ZOOMING_VALUE));
    }

    private void renderViews() {
        renderHeaders();
        renderTimezones();
        new loadDetailsTask().execute();
    }

    private void renderHeaders() {
        tvCountryName.setText(country.name);
        tvCapital.setText(country.capital);
        loadFlag();
    }

    private void renderTimezones() {
        String timezones = country.timezones.replace("[", "");
        timezones = timezones.replace("]", "");
        tvTimezones.setText(timezones);
    }

    private void loadFlag() {
        Uri uri = Uri.parse(country.flag);
        requestBuilder.load(uri).into(ivFlag);
    }

    class loadDetailsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            languageList = dbClient.getLanguages(country.alpha3Code);
            currencyList = dbClient.getCurrencies(country.alpha3Code);
            regionalBlocList = dbClient.getRegionalBlocs(country.alpha3Code);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setLanguage();
            setCurrency();
            setRegionalBloc();
        }
    }

    private void setLanguage() {
        if (!languageList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Language language : languageList) {
                stringBuilder.append(language.name);
                stringBuilder.append(",");
            }

            String language = stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
            tvLanguage.setText(language);
        } else {
            llLanguage.setVisibility(View.GONE);
        }
    }

    private void setCurrency() {
        if (!currencyList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Currency currency : currencyList) {
                stringBuilder.append(currency.name);
                stringBuilder.append(",");
            }

            String currency = stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
            tvCurrency.setText(currency);
        } else {
            llCurrency.setVisibility(View.GONE);
        }
    }

    private void setRegionalBloc() {
        if (!regionalBlocList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (RegionalBloc regionalBloc : regionalBlocList) {
                stringBuilder.append(regionalBloc.acronym);
                stringBuilder.append(",");
            }

            String currency = stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
            tvRegionalBloc.setText(currency);
        } else {
            llRegionalBloc.setVisibility(View.GONE);
        }
    }
}