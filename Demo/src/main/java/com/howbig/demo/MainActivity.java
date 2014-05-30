package com.howbig.demo;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.howbig.riot.api.ApiService;
import com.howbig.riot.api.ChampionRequestDeserializer;
import com.howbig.riot.api.DragonService;
import com.howbig.riot.api.SpellDeserializer;
import com.howbig.riot.api.VarsDeserializer;
import com.howbig.riot.type.Champion;
import com.howbig.riot.type.ChampionJsonResponse;
import com.howbig.riot.type.Spell;
import com.howbig.riot.type.Vars;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


public class MainActivity extends ActionBarActivity {
    private static final String API_KEY = "ee58ba39-9b05-4d80-a245-01a33e1fbf0f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .registerTypeAdapter(ChampionJsonResponse.class, new ChampionRequestDeserializer())
                        .registerTypeAdapter(Spell.class, new SpellDeserializer())
                        .registerTypeAdapter(Vars.class, new VarsDeserializer())
                        .create();

                RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://prod.api.pvp.net/api/lol")
                    .build();
                ApiService service = restAdapter.create(ApiService.class);


                String[] versions = service.getVersions(ApiService.API_LANGUAGE, ApiService.API_VERSION,API_KEY);

                for(String v: versions)
                    Log.d("Test", v);
                String version = versions[0];

                restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://ddragon.leagueoflegends.com/cdn/" + version)
                        .setConverter(new GsonConverter(gson))
                        .build();
                DragonService dragonService = restAdapter.create(DragonService.class);

                ChampionJsonResponse asheResponse = dragonService.getChampion("Ashe");
                Champion ashe = asheResponse.data;
                String test = ashe.name;
                return null;
            }
        }.execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}