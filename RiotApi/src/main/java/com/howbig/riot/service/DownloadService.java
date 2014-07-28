package com.howbig.riot.service;

import android.app.IntentService;
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
import com.howbig.riot.type.Vars;
import com.howbig.riot.type.champion.Blocks;
import com.howbig.riot.type.champion.Champion;
import com.howbig.riot.type.champion.ChampionFullJsonResponse;
import com.howbig.riot.type.champion.ChampionJsonResponse;
import com.howbig.riot.type.champion.Spell;
import com.howbig.riot.type.item.Item;
import com.howbig.riot.type.item.ItemsJsonResponse;
import com.howbig.riot.type.mastery.Mastery;
import com.howbig.riot.type.mastery.MasteryJsonResponse;
import com.howbig.riot.type.rune.Rune;
import com.howbig.riot.type.rune.RuneJsonResponse;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class DownloadService extends IntentService {

    private static final String API_KEY = "ee58ba39-9b05-4d80-a245-01a33e1fbf0f";

    private static final String ACTION_DOWNLOAD_VERSIONS = "com.howbig.riot.service.action.download.VERSIONS";
    private static final String ACTION_DOWNLOAD_CHAMPION = "com.howbig.riot.service.action.download.CHAMPION";
    private static final String ACTION_DOWNLOAD_CHAMPION_FULL = "com.howbig.riot.service.action.download.CHAMPIONFULL";
    private static final String ACTION_DOWNLOAD_CHAMPION_SQUARE_IMAGE = "com.howbig.riot.service.action.download.CHAMPIONSQUAREIMAGE";
    private static final String CHAMPION_NAME = "com.howbig.riot.service.extra.CHAMPION_NAME";
    private static final String CHAMPION_SQUARE_IMAGE = "com.howbig.riot.service.extra.CHAMPION_SQUARE_IMAGE";

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

//    public static void startChampionSquareImageDownload(Context context, String championName) {
//        Intent intent = new Intent(context, DownloadService.class);
//        intent.setAction(ACTION_DOWNLOAD_CHAMPION);
//        intent.putExtra(CHAMPION_NAME, championName);
//        context.startService(intent);
//    }

    public static void startChampionFullDownload(Context context) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_DOWNLOAD_CHAMPION_FULL);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD_VERSIONS.equals(action)) {
                handleDownloadVersions();
            } else if (ACTION_DOWNLOAD_CHAMPION.equals(action)) {
                final String url = intent.getStringExtra(CHAMPION_NAME);
                handleDownloadChampion(url);
            } else if (ACTION_DOWNLOAD_CHAMPION_FULL.equals(action)) {
                handleDownloadAllChampions();
            }
//            else if (ACTION_DOWNLOAD_CHAMPION_SQUARE_IMAGE.equals(action)) {
//                final String url = intent.getStringExtra(CHAMPION_SQUARE_IMAGE);
//                handleDownloadChampionSquareImage(url);
//            }
        }
    }

    private void handleDownloadVersions() {
        long start = System.nanoTime();
        String[] versions = service.getVersions(ApiService.API_LANGUAGE, ApiService.API_VERSION, API_KEY);
        Log.d("DownloadService", "Get Versions Time:" + (System.nanoTime() - start) / 1000000);

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

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        String localVersion = getSharedPreferences("RiotAPI", MODE_PRIVATE).getString("localVersion", "0");
        if (versionCompare(localVersion, version) < 0) {
            //Do full rebuild
            handleDownloadAllChampions();
            handleDownloadAllItems();
            handleDownloadAllMasteries();

        }

    }

    private void handleDownloadAllItems() {
        long start = System.nanoTime();
        ItemsJsonResponse itemsJsonResponse = dragonService.getItems();
        Log.d("DownloadService", "Get All Items Time: " + (System.nanoTime() - start) / 1000000);

        long startInsert = System.nanoTime();
        for (Item item : itemsJsonResponse.data) {
            getContentResolver().insert(Item.CONTENT_URI, item.getAsContentValues());
        }
        Log.d("DownloadService", "Insert All Items Time: " + (System.nanoTime() - startInsert) / 1000000);
    }

    private void handleDownloadAllMasteries() {
        long start = System.nanoTime();
        MasteryJsonResponse masteryJsonResponse = dragonService.getMastery();
        Log.d("DownloadService", "Get All Masteries Time: " + (System.nanoTime() - start) / 1000000);

        long startInsert = System.nanoTime();
        for (Mastery mastery : masteryJsonResponse.data) {
            getContentResolver().insert(Mastery.CONTENT_URI, mastery.getAsContentValues());
        }
        Log.d("DownloadService", "Insert All Masteries Time: " + (System.nanoTime() - startInsert) / 1000000);
    }

    private void handleDownloadAllChampions() {
        long start = System.nanoTime();
        ChampionFullJsonResponse championJsonResponse = dragonService.getChampionFull();
        Log.d("DownloadService", "Download ChampionFull Time: " + (System.nanoTime() - start) / 1000000);

        long startInsert = System.nanoTime();
        for (Champion champ : championJsonResponse.data.values()) {
            getContentResolver().insert(Champion.CONTENT_URI, champ.getAsContentValues());
        }
        Log.d("DownloadService", "Insert ChampionFull Time: " + (System.nanoTime() - startInsert) / 1000000);
    }

    private void handleDownloadAllRunes() {
        long start = System.nanoTime();
        RuneJsonResponse runeJsonResponse = dragonService.getRune();
        Log.d("DownloadService", "Download All Runes Time: " + (System.nanoTime() - start) / 1000000);

        long startInsert = System.nanoTime();
        for (Rune rune : runeJsonResponse.data) {
            getContentResolver().insert(Rune.CONTENT_URI, rune.getAsContentValues());
        }
        Log.d("DownloadService", "Insert All Runes Time: " + (System.nanoTime() - startInsert) / 1000000);
    }

    private void handleDownloadChampion(String championName) {
        long start = System.nanoTime();
        ChampionJsonResponse championJsonResponse = dragonService.getChampion(championName);
        Log.d("DownloadService", "Get Champion Time: " + championName + " " + (System.nanoTime() - start) / 1000000);
        Champion champ = championJsonResponse.data;
        getContentResolver().insert(Champion.CONTENT_URI, champ.getAsContentValues());

    }

//    private void handleDownloadChampionSquareImage(String imageUrl) {
//        long start = System.nanoTime();
//        ImageLoader.getInstance().load;
//        Log.d("DownloadService", "Get Download Time: " + imageUrl + " " + (System.nanoTime() - start) / 1000000);
//        ;
//
//    }


    /**
     * Compares two version strings.
     * <p/>
     * Use this instead of String.compareTo() for a non-lexicographical
     * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
     *
     * @param str1 a string of ordinal numbers separated by decimal points.
     * @param str2 a string of ordinal numbers separated by decimal points.
     * @return The result is a negative integer if str1 is _numerically_ less than str2.
     * The result is a positive integer if str1 is _numerically_ greater than str2.
     * The result is zero if the strings are _numerically_ equal.
     * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
     */
    private Integer versionCompare(String str1, String str2) {
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        // set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        // compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        // the strings are equal or one string is a substring of the other
        // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        else {
            return Integer.signum(vals1.length - vals2.length);
        }
    }
}
