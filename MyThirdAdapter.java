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

public class MyThirdAdapter extends ArrayAdapter<ModelHelpline> {

    private Context context;
    private final List<ModelHelpline> modelHelplineList;
    private List<ModelHelpline> modelHelplineListFiltered;

    public MyThirdAdapter(Context context, List<ModelHelpline> modelHelplineList) {
        super(context, R.layout.list_helpline,modelHelplineList);

        this.context=context;
        this.modelHelplineList=modelHelplineList;
        this.modelHelplineListFiltered=modelHelplineList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_helpline, null, true);
        TextView tvStateName=view.findViewById(R.id.tvStateName);
        TextView tvHelpline=view.findViewById(R.id.tvHelpline);

        tvStateName.setText(modelHelplineListFiltered.get(position).getState());
        tvHelpline.setText(modelHelplineListFiltered.get(position).getHelpline());

        return view;
    }

    @Override
    public int getCount() {
        return modelHelplineListFiltered.size();
    }

    @Nullable
    @Override
    public ModelHelpline getItem(int position) {
        return modelHelplineListFiltered.get(position);
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
                    filterResults.count=modelHelplineList.size();
                    filterResults.values=modelHelplineList;
                }
                else{
                    List<ModelHelpline> resultsHelplineModel=new ArrayList<>();
                    String searchStr=charSequence.toString().toLowerCase();

                    for(ModelHelpline itemModel:modelHelplineList){
                        if(itemModel.getState().toLowerCase().contains(searchStr)){
                            resultsHelplineModel.add(itemModel);
                        }
                        filterResults.count=resultsHelplineModel.size();
                        filterResults.values=resultsHelplineModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                modelHelplineListFiltered=(List<ModelHelpline>) filterResults.values;
                HelplineActivity.modelHelplineList=(List<ModelHelpline>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public void clearData(){
        modelHelplineList.clear();
        modelHelplineListFiltered.clear();
    }
}
