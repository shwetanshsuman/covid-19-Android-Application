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

import java.util.ArrayList;
import java.util.List;

public class HelplineActivity extends AppCompatActivity {

    EditText edtSearch;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<ModelHelpline> modelHelplineList=new ArrayList<>();
    ModelHelpline modelHelpline;
    MyThirdAdapter myThirdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);

        edtSearch=findViewById(R.id.helpSearch);
        listView=findViewById(R.id.listHelpView);
        simpleArcLoader=findViewById(R.id.loader);

        getSupportActionBar().setTitle("Helpline");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fetchData();

 //       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 //           @Override
 //           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
 //               startActivity(new Intent(getApplicationContext(),DetailActivity.class).putExtra("position",i));
 //           }
 //       });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myThirdAdapter.getFilter().filter(charSequence);
                myThirdAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            myThirdAdapter.clearData();
            myThirdAdapter.notifyDataSetChanged();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void fetchData() {

        String url= "https://api.rootnet.in/covid19-in/contacts";
        simpleArcLoader.start();

        StringRequest request= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONObject("contacts").getJSONArray("regional");

                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject data=jsonArray.getJSONObject(i);

                                String stateName=data.getString("loc");
                                String helpline=data.getString("number");


                                modelHelpline =new ModelHelpline(stateName,helpline);
                                modelHelplineList.add(modelHelpline);
                            }
                            myThirdAdapter=new MyThirdAdapter(HelplineActivity.this,modelHelplineList);
                            listView.setAdapter(myThirdAdapter);
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
                Toast.makeText(HelplineActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}