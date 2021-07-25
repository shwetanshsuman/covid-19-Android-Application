package com.shwetansh.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MySecondAdapter extends ArrayAdapter<ModelVaccinated> {

    private Context context;
    private final List<ModelVaccinated> modelVaccinatedList;
    private List<ModelVaccinated> modelVaccinatedListFiltered;

    public MySecondAdapter(Context context, List<ModelVaccinated> modelVaccinatedList) {
        super(context, R.layout.list_customtwo,modelVaccinatedList);

        this.context=context;
        this.modelVaccinatedList=modelVaccinatedList;
        this.modelVaccinatedListFiltered=modelVaccinatedList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customtwo, null, true);
        TextView CountryName=view.findViewById(R.id.CountryName);

        CountryName.setText(modelVaccinatedListFiltered.get(position).getCountry());

        return view;
    }


    @Override
    public int getCount() {
        return modelVaccinatedListFiltered.size();
    }

    @Nullable
    @Override
    public ModelVaccinated getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter1=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResult=new FilterResults();
                if(charSequence==null || charSequence.length()==0){
                    filterResult.count=modelVaccinatedList.size();
                    filterResult.values=modelVaccinatedList;
                }
                else{
                    List<ModelVaccinated> resultModel=new ArrayList<>();
                    String searchStr=charSequence.toString().toLowerCase();

                    for(ModelVaccinated itemModel:modelVaccinatedList){
                        if(itemModel.getCountry().toLowerCase().contains(searchStr)){
                            resultModel.add(itemModel);
                        }
                        filterResult.count=resultModel.size();
                        filterResult.values=resultModel;
                    }
                }
                return filterResult;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                modelVaccinatedListFiltered=(List<ModelVaccinated>) filterResults.values;
                Vaccinated.modelVaccinatedList=(List<ModelVaccinated>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter1;
    }

    public void clearData(){
        modelVaccinatedList.clear();
        modelVaccinatedListFiltered.clear();
    }
}
