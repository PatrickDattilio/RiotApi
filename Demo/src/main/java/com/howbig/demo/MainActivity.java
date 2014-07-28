package com.howbig.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.service.DownloadService;
import com.howbig.riot.type.champion.Champion;


public class MainActivity extends ActionBarActivity {
    private static final String API_KEY = "ee58ba39-9b05-4d80-a245-01a33e1fbf0f";

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        DownloadService.startVersionsDownload(this);
        DownloadService.startChampionFullDownload(this);


        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
//                Gson gson = new GsonBuilder()
//
//                        .registerTypeAdapter(ChampionJsonResponse.class, new ChampionRequestDeserializer())
//                        .registerTypeAdapter(Spell.class, new SpellDeserializer())
//                        .registerTypeAdapter(Vars.class, new VarsDeserializer())
//                        .registerTypeAdapter(Blocks.class, new BlocksDeserializer())
//                        .registerTypeAdapter(Item.class, new ItemDeserializer())
//                        .registerTypeAdapter(ItemsJsonResponse.class, new ItemJsonDeserializer())
//                        .registerTypeAdapter(Rune.class, new RuneDeserializer())
//                        .registerTypeAdapter(RuneJsonResponse.class, new RuneJsonDeserializer())
//                        .registerTypeAdapter(Mastery.class, new MasteryDeserializer())
//                        .registerTypeAdapter(MasteryJsonResponse.class, new MasteryJsonDeserializer())
//                        .create();
//
//                RestAdapter restAdapter = new RestAdapter.Builder()
//                    .setEndpoint("https://prod.api.pvp.net/api/lol")
//                    .build();
//                ApiService service = restAdapter.create(ApiService.class);
//
//
//                String[] versions = service.getVersions(ApiService.API_LANGUAGE, ApiService.API_VERSION,API_KEY);
//
//                for(String v: versions)
//                    Log.d("Test", v);
//                String version = versions[0];
//
//                restAdapter = new RestAdapter.Builder()
//                        .setEndpoint("http://ddragon.leagueoflegends.com/cdn/" + version)
//                        .setConverter(new GsonConverter(gson))
//                        .build();
//                DragonService dragonService = restAdapter.create(DragonService.class);
//
//                ItemsJsonResponse items= dragonService.getItems();
//                RuneJsonResponse runes= dragonService.getRune();
//                MasteryJsonResponse masteries=dragonService.getMastery();
//
//
//                ChampionJsonResponse asheResponse = dragonService.getChampion("Ashe");
                long start = System.nanoTime();
                Cursor query = getContentResolver().query(Champion.CONTENT_URI, new String[]{DBHelper.KEY_ID, DBHelper.KEY_NAME, DBHelper.KEY_TAGS}, null, null, null);
                Log.d("MainActivty", "Query time:" + (System.nanoTime() - start) / 1000000);
                long startParse = System.nanoTime();
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    Champion champ = new Champion();
                    champ.id = query.getString(0);
                    champ.name = query.getString(1);
                    champ.tags = query.getString(2).split(",");
                    query.moveToNext();
                    long startImageDownload = System.nanoTime();
                    // DownloadService.startChampionSquareImageDownload(MainActivity.this,"http://ddragon.leagueoflegends.com/cdn/4.7.8/img/champion/"+champ.image.full);
                    Log.d("MainActivty", "Download Image:" + (System.nanoTime() - startImageDownload) / 1000000);
                }
                Log.d("MainActivty", "Parse json to Champions:" + (System.nanoTime() - startParse) / 1000000);
                String test = "test";
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
