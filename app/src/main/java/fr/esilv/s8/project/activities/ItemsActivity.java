package fr.esilv.s8.project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fr.esilv.s8.project.Constants;
import fr.esilv.s8.project.R;
import fr.esilv.s8.project.adapters.ItemsAdapter;
import fr.esilv.s8.project.interfaces.OnInfosSelectedListener;
import fr.esilv.s8.project.models.Infos;
import fr.esilv.s8.project.models.Items;
import fr.esilv.s8.project.models.Search;

/**
 * Created by Harry on 20/03/2017.
 */

public class ItemsActivity extends AppCompatActivity implements OnInfosSelectedListener {

    private static final String URL = "https://www.googleapis.com/youtube/v3/search";
    private RecyclerView recyclerView;
    private String searchKey;

    //https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=50&type=video&q=AndroidStudio&key=AIzaSyA45BUbNv7xly18Qj46HeNCl-JVW3vp0gg

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if(intent.hasExtra("text")) {
            String text = intent.getStringExtra("text");
            searchKey = text;
        }

        searchKey = searchKey.replace(" ", "%20");

        getSearch();
    }

    private void getSearch() {
        StringRequest search = new StringRequest(URL + "?part=snippet&maxResults=50&type=video&q="+searchKey+"&key="+Constants.API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //parse data from webservice to get Contracts as Java object
                Log.e("Infos", response);
                Log.e("Infos", URL + "?part=snippet&maxResults=50&type=video&q=" + searchKey + "&key=" + Constants.API_KEY);
                Search search = new Gson().fromJson(response, Search.class);

                setAdapter(search.getItems());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Infos", "Error");
            }
        });

        Volley.newRequestQueue(this).add(search);
    }

    private void setAdapter(Items items) {
        ItemsAdapter adapter = new ItemsAdapter(items);
        adapter.setOnInfosSelectedListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onInfosSelected(Infos infos) {
        PlayVideoActivity.start(this, infos);
    }
}
