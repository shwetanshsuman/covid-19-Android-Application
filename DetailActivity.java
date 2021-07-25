package com.shwetansh.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;
    TextView tvCountry,tvCases,tvTodayCases,tvDeaths,tvTodayDeaths,tvRecovered,tvActive,tvCritical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+trackCountries.modelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvCountry=findViewById(R.id.tvCountry);
        tvCases=findViewById(R.id.cases);
        tvTodayCases=findViewById(R.id.todayCases);
        tvActive=findViewById(R.id.Active);
        tvCritical=findViewById(R.id.critical);
        tvDeaths=findViewById(R.id.deaths);
        tvRecovered=findViewById(R.id.recovered);
        tvTodayDeaths=findViewById(R.id.todayDeaths);

        tvCountry.setText(trackCountries.modelList.get(positionCountry).getCountry());
        tvCases.setText(trackCountries.modelList.get(positionCountry).getCases());
        tvTodayCases.setText(trackCountries.modelList.get(positionCountry).getTodayCases());
        tvDeaths.setText(trackCountries.modelList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(trackCountries.modelList.get(positionCountry).getTodayDeaths());
        tvRecovered.setText(trackCountries.modelList.get(positionCountry).getRecovered());
        tvActive.setText(trackCountries.modelList.get(positionCountry).getActive());
        tvCritical.setText(trackCountries.modelList.get(positionCountry).getCritical());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}