package com.shwetansh.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Detail_Activity2 extends AppCompatActivity {

    private int positionCountry;
    TextView tvCountry,tvVaccinated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_2);


        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+Vaccinated.modelVaccinatedList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvCountry=findViewById(R.id.country);
        tvVaccinated=findViewById(R.id.vaccinated);

        tvCountry.setText(Vaccinated.modelVaccinatedList.get(positionCountry).getCountry());
        tvVaccinated.setText(Vaccinated.modelVaccinatedList.get(positionCountry).getTimeline());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}