package com.dogancankilic.klasikfilmler.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;
import com.dogancankilic.klasikfilmler.R;
import com.dogancankilic.klasikfilmler.adapters.MainAdapter;
import com.dogancankilic.klasikfilmler.model.Model;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class MainActivity extends Activity {

    private final String JSON_URL ="http://api.dogancankilic.com/movie_api_test.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Model> lstModel;
    private RecyclerView recyclerView;
    private AdView mAdView;



    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lstModel = new ArrayList<>();
        recyclerView = findViewById(R.id.recview);


        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        // Checking the internet connection
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            jsonrequest();


        }
        else {
            Toast.makeText(this, "İnternete bağlı olduğunuzdan emin olun ve yenileyin.", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, SwipeToRefresh.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
        }




        /*jsonrequest();*/
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-6708825800902548/3725036211");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        
    }

    private void jsonrequest() {

        request=new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                JSONObject jsonObject = null;


                for (int i = 0; i<response.length();i++){

                    try {
                        jsonObject = response.getJSONObject(i);
                        Model model = new Model();
                        model.setName(jsonObject.getString("name"));
                        model.setCategory(jsonObject.getString("category"));
                        model.setRating(jsonObject.getString("rating"));
                        model.setStudio(jsonObject.getString("studio"));
                        model.setImage_url(jsonObject.getString("img"));
                        model.setMovie_link(jsonObject.getString("movie_link"));
                        lstModel.add(model);




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                setuprecyclerview(lstModel);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,  "An error occured.");

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

    private void setuprecyclerview(List<Model> lstModel) {

        MainAdapter myadapter = new MainAdapter(this,lstModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);



    }
}
