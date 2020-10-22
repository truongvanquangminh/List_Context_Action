package com.example.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<CountriesModel> countriesData;
    LayoutInflater layoutInflater;
    CountriesModel countriesModel;

    public CustomAdapter(Context context, ArrayList<CountriesModel> countriesData) {
        this.context = context;
        this.countriesData = countriesData;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return countriesData.size();
    }

    @Override
    public Object getItem(int position) {
        return countriesData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return countriesData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView==null) {
            rowView = layoutInflater.inflate(R.layout.country_row, null, true);
        }
        //link views
        ImageView countryFlagIv = rowView.findViewById(R.id.image);
        TextView countryNameTv = rowView.findViewById(R.id.nameCountry);
        TextView populationTv = rowView.findViewById(R.id.population);
        TextView areaTv = rowView.findViewById(R.id.area);

        countriesModel = countriesData.get(position);

        countryFlagIv.setImageResource(countriesModel.getImage());
        countryNameTv.setText(countriesModel.getName());
        populationTv.setText("Population : " + countriesModel.getPopulation());
        areaTv.setText("Area : " + countriesModel.getArea());

        return rowView;
    }
    //Delete ListView
    public void removeItems(ArrayList<CountriesModel> items){
        for (CountriesModel item : items){
            countriesData.remove(item);
        }
        notifyDataSetChanged();
    }
}
