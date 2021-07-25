package com.shwetansh.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<Model> {

    private Context context;
    private final List<Model> modelList;
    private List<Model> modelListFiltered;

    public MyCustomAdapter(Context context, List<Model> modelList) {
        super(context, R.layout.list_custom,modelList);

        this.context=context;
        this.modelList=modelList;
        this.modelListFiltered=modelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom, null, true);
        TextView tvCountryName=view.findViewById(R.id.tvCountryName);
        ImageView imageView=view.findViewById(R.id.imageFlag);

        tvCountryName.setText(modelListFiltered.get(position).getCountry());
        Glide.with(context).load(modelListFiltered.get(position).getFlag()).into(imageView);

        return view;
    }


    @Override
    public int getCount() {
        return modelListFiltered.size();
    }

    @Nullable
    @Override
    public Model getItem(int position) {
        return modelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults=new FilterResults();
                if(charSequence==null || charSequence.length()==0){
                    filterResults.count=modelList.size();
                    filterResults.values=modelList;
                }
                else{
                    List<Model> resultsModel=new ArrayList<>();
                    String searchStr=charSequence.toString().toLowerCase();

                    for(Model itemsModel:modelList){
                       if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                           resultsModel.add(itemsModel);
                       }
                       filterResults.count=resultsModel.size();
                       filterResults.values=resultsModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                modelListFiltered=(List<Model>) filterResults.values;
                trackCountries.modelList=(List<Model>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public void clearData(){
        modelList.clear();
        modelListFiltered.clear();
    }
}
