package com.howbig.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.howbig.riot.api.deserializers.BlocksDeserializer;
import com.howbig.riot.api.deserializers.ChampionRequestDeserializer;
import com.howbig.riot.api.deserializers.ItemDeserializer;
import com.howbig.riot.api.deserializers.ItemJsonDeserializer;
import com.howbig.riot.api.deserializers.MasteryDeserializer;
import com.howbig.riot.api.deserializers.MasteryJsonDeserializer;
import com.howbig.riot.api.deserializers.RuneDeserializer;
import com.howbig.riot.api.deserializers.RuneJsonDeserializer;
import com.howbig.riot.api.deserializers.SpellDeserializer;
import com.howbig.riot.api.deserializers.SummonerDeserializer;
import com.howbig.riot.api.deserializers.SummonerJsonDeserializer;
import com.howbig.riot.api.deserializers.VarsDeserializer;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.service.DownloadService;
import com.howbig.riot.type.Vars;
import com.howbig.riot.type.champion.Blocks;
import com.howbig.riot.type.champion.Champion;
import com.howbig.riot.type.champion.ChampionJsonResponse;
import com.howbig.riot.type.champion.Spell;
import com.howbig.riot.type.item.Item;
import com.howbig.riot.type.item.ItemsJsonResponse;
import com.howbig.riot.type.mastery.Mastery;
import com.howbig.riot.type.mastery.MasteryJsonResponse;
import com.howbig.riot.type.rune.Rune;
import com.howbig.riot.type.rune.RuneJsonResponse;
import com.howbig.riot.type.summoner.Summoner;
import com.howbig.riot.type.summoner.SummonerJsonResponse;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


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
                long start = System.nanoTime();
                Cursor cursor = getContentResolver().query(Champion.CONTENT_URI, new String[]{DBHelper.KEY_JSON}, null, null, null);
                Log.d("MainActivty", "Query time:" + (System.nanoTime() - start) / 1000000);
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(ChampionJsonResponse.class, new ChampionRequestDeserializer())
                        .registerTypeAdapter(Spell.class, new SpellDeserializer())
                        .registerTypeAdapter(Vars.class, new VarsDeserializer())
                        .registerTypeAdapter(Blocks.class, new BlocksDeserializer())
                        .registerTypeAdapter(Item.class, new ItemDeserializer())
                        .registerTypeAdapter(ItemsJsonResponse.class, new ItemJsonDeserializer())
                        .registerTypeAdapter(Rune.class, new RuneDeserializer())
                        .registerTypeAdapter(RuneJsonResponse.class, new RuneJsonDeserializer())
                        .registerTypeAdapter(Mastery.class, new MasteryDeserializer())
                        .registerTypeAdapter(MasteryJsonResponse.class, new MasteryJsonDeserializer())
                        .registerTypeAdapter(Summoner.class, new SummonerDeserializer())
                        .registerTypeAdapter(SummonerJsonResponse.class, new SummonerJsonDeserializer())
                        .create();
                long startParse = System.nanoTime();
                cursor.moveToFirst();
                DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .build();
                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                        .defaultDisplayImageOptions(defaultOptions)
                        .build();
                ImageLoader.getInstance().init(config);
                ImageLoader loader = ImageLoader.getInstance();
                while (!cursor.isAfterLast()) {
                    Champion champ = gson.fromJson(cursor.getString(0), Champion.class);
                    cursor.moveToNext();
                    long startImageDownload = System.nanoTime();

                    loader.loadImage("http://ddragon.leagueoflegends.com/cdn/4.7.8/img/champion/" + champ.image.full, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {

                        }
                    });
                    // DownloadService.startChampionSquareImageDownload(MainActivity.this,"http://ddragon.leagueoflegends.com/cdn/4.7.8/img/champion/"+champ.image.full);
                    Log.d("MainActivty", "Download Image:" + (System.nanoTime() - startImageDownload) / 1000000);
                }
                cursor.close();
                Log.d("MainActivty", "Parse json to Champions:" + (System.nanoTime() - startParse) / 1000000);
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
