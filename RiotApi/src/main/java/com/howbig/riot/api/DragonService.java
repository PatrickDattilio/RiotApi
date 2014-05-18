package com.howbig.riot.api;

import com.howbig.riot.type.ChampionJsonResponse;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public interface DragonService {

    @GET("/data/en_US/champion/{name}.json")
    ChampionJsonResponse getChampion(@Path("name") String championName);
}
