package com.onetouchcode.countrypedia;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.onetouchcode.countrypedia.model.Country;
import com.onetouchcode.countrypedia.svgutil.GlideApp;
import com.onetouchcode.countrypedia.svgutil.SvgSoftwareLayerSetter;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> implements Filterable {

    interface ClickListener {
        void onClick(Country country);
    }

    private Context context;
    private List<Country> countryList;
    private List<Country> filteredCountryList;
    private RequestBuilder<PictureDrawable> requestBuilder = null;
    private ClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView tvCountryName, tvCapitalName;
        ImageView ivFlag;

        public MyViewHolder(View view) {
            super(view);
            rootView = view;
            tvCountryName = view.findViewById(R.id.tvCountryName);
            tvCapitalName = view.findViewById(R.id.tvCapitalName);
            ivFlag = view.findViewById(R.id.ivFlag);
        }
    }


    public CountryAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
        this.filteredCountryList = countryList;
        clickListener = (ClickListener) context;
        requestBuilder = GlideApp.with(context)
                .as(PictureDrawable.class)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Country country = filteredCountryList.get(position);
        holder.tvCountryName.setText(country.name);
        holder.tvCapitalName.setText(country.capital);

        loadFlag(holder.ivFlag, country.flag);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(country);
            }
        });
    }

    private void loadFlag(ImageView imageView, String url) {
        Uri uri = Uri.parse(url);
        requestBuilder.load(uri).into(imageView);
    }

    public Country getItem(int position) {
        return filteredCountryList.get(position);
    }

    @Override
    public int getItemCount() {
        return filteredCountryList.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString().toLowerCase();

                if (charString.isEmpty()) {

                    filteredCountryList = countryList;
                } else {
                    List<Country> filteredList = new ArrayList<>();
                    for (Country country : countryList) {
                        if (country.name.toLowerCase().contains(charString)
                                || country.altSpellings.toLowerCase().contains(charString)
                                || country.capital.toLowerCase().contains(charString)
                                || country.region.toLowerCase().contains(charString)
                                || country.subRegion.toLowerCase().contains(charString)
                                || country.topLevelDomain.toLowerCase().contains(charString)
                                || country.alpha2Code.toLowerCase().contains(charString)
                                || country.alpha3Code.toLowerCase().contains(charString)
                                || country.demonym.toLowerCase().contains(charString)
                                || country.nativeName.toLowerCase().contains(charString)) {

                            filteredList.add(country);
                        }
                    }

                    filteredCountryList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredCountryList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredCountryList = (List<Country>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}