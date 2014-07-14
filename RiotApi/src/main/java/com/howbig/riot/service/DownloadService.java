package com.howbig.riot.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.howbig.riot.api.ApiService;
import com.howbig.riot.api.BlocksDeserializer;
import com.howbig.riot.api.ChampionRequestDeserializer;
import com.howbig.riot.api.DragonService;
import com.howbig.riot.api.ItemDeserializer;
import com.howbig.riot.api.ItemJsonDeserializer;
import com.howbig.riot.api.RuneDeserializer;
import com.howbig.riot.api.RuneJsonDeserializer;
import com.howbig.riot.api.SpellDeserializer;
import com.howbig.riot.api.VarsDeserializer;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.type.Vars;
import com.howbig.riot.type.champion.Blocks;
import com.howbig.riot.type.champion.Champion;
import com.howbig.riot.type.champion.ChampionJsonResponse;
import com.howbig.riot.type.champion.Spell;
import com.howbig.riot.type.item.Item;
import com.howbig.riot.type.item.ItemsJsonResponse;
import com.howbig.riot.type.rune.Rune;
import com.howbig.riot.type.rune.RuneJsonResponse;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {

    private static final String API_KEY = "ee58ba39-9b05-4d80-a245-01a33e1fbf0f";

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.howbig.riot.service.action.FOO";
    private static final String ACTION_BAZ = "com.howbig.riot.service.action.BAZ";

    private static final String ACTION_DOWNLOAD_VERSIONS = "com.howbig.riot.service.action.download.VERSIONS";
    private static final String ACTION_DOWNLOAD_CHAMPION = "com.howbig.riot.service.action.download.CHAMPION";
    private static final String CHAMPION_NAME = "com.howbig.riot.service.extra.CHAMPION_NAME";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.howbig.riot.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.howbig.riot.service.extra.PARAM2";
    private final ApiService service;
    private DragonService dragonService;
    private RestAdapter restAdapter;

    public DownloadService() {
        super("DownloadService");
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://prod.api.pvp.net/api/lol")
                .build();
        service = restAdapter.create(ApiService.class);

    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startVersionsDownload(Context context) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_DOWNLOAD_VERSIONS);
        context.startService(intent);
    }

    public static void startChampionDownload(Context context, String championName) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_DOWNLOAD_CHAMPION);
        intent.putExtra(CHAMPION_NAME, championName);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            } else if (ACTION_DOWNLOAD_VERSIONS.equals(action)) {
                handleDownloadVersions();
            } else if (ACTION_DOWNLOAD_CHAMPION.equals(action)) {
                final String url = intent.getStringExtra(CHAMPION_NAME);
                handleDownloadChampion(url);

            }
        }
    }

    private void handleDownloadVersions() {

        String[] versions = service.getVersions(ApiService.API_LANGUAGE, ApiService.API_VERSION, API_KEY);

        for (String v : versions)
            Log.d("Test", v);
        String version = versions[0];

        Gson gson = new GsonBuilder()

                .registerTypeAdapter(ChampionJsonResponse.class, new ChampionRequestDeserializer())
                .registerTypeAdapter(Spell.class, new SpellDeserializer())
                .registerTypeAdapter(Vars.class, new VarsDeserializer())
                .registerTypeAdapter(Blocks.class, new BlocksDeserializer())
                .registerTypeAdapter(Item.class, new ItemDeserializer())
                .registerTypeAdapter(ItemsJsonResponse.class, new ItemJsonDeserializer())
                .registerTypeAdapter(Rune.class, new RuneDeserializer())
                .registerTypeAdapter(RuneJsonResponse.class, new RuneJsonDeserializer())
                .create();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://ddragon.leagueoflegends.com/cdn/" + version)
                .setConverter(new GsonConverter(gson))
                .build();
        dragonService = restAdapter.create(DragonService.class);
    }

    private void handleDownloadChampion(String championName) {
        ChampionJsonResponse championJsonResponse = dragonService.getChampion(championName);
        Champion champ = championJsonResponse.data;
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_ID, champ.id);
        values.put(DBHelper.KEY_NAME, champ.name);
        values.put(DBHelper.KEY_TAGS, champ.tags[0]);
        values.put(DBHelper.KEY_JSON, new Gson().toJson(champ));

        getContentResolver().insert(Champion.CONTENT_URI, values);

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
