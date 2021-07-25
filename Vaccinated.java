package com.shwetansh.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Vaccinated extends AppCompatActivity {

    EditText search;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<ModelVaccinated> modelVaccinatedList=new ArrayList<>();
    ModelVaccinated modelVaccinated;
    MySecondAdapter mySecondAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinated);


        search=findViewById(R.id.edtVaccinatedSearch);
        listView=findViewById(R.id.listViewVaccinated);
        simpleArcLoader=findViewById(R.id.vacloader);

        getSupportActionBar().setTitle("Vaccinated Population");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fetchData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),Detail_Activity2.class).putExtra("position",i));
            }
        });



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mySecondAdapter.getFilter().filter(charSequence);
                mySecondAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            mySecondAdapter.clearData();
            mySecondAdapter.notifyDataSetChanged();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {

        String url= "https://disease.sh/v3/covid-19/vaccine/coverage/countries?lastdays";
        simpleArcLoader.start();


        StringRequest request= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONArray jsonArray=new JSONArray(response);

                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String countryName=jsonObject.getString("country");
                                JSONObject object=jsonObject.getJSONObject("timeline");
                                String timeline=object.getString("7/24/21");

                                modelVaccinated =new ModelVaccinated(countryName,timeline);
                                modelVaccinatedList.add(modelVaccinated);
                            }
                            mySecondAdapter=new MySecondAdapter(Vaccinated.this,modelVaccinatedList);
                            listView.setAdapter(mySecondAdapter);
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);

                        }catch (JSONException e){
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(Vaccinated.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}
