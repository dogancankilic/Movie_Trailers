package com.dogancankilic.klasikfilmler.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dogancankilic.klasikfilmler.R;

public class SwipeToRefresh extends Activity {

    SwipeRefreshLayout swp_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_to_refresh);

        swp_layout = findViewById(R.id.swp);

        swp_layout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorPrimaryDark));

        swp_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*swp_layout.setRefreshing(true);*/
                ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
                // ARE WE CONNECTED TO THE NET
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()){
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                }
                else{
                    Toast.makeText(SwipeToRefresh.this, "İnternete bağlı olduğunuzdan emin olun ve yenileyin.", Toast.LENGTH_LONG).show();
                    swp_layout.setRefreshing(false);
                }

            }
        });




    }
}
