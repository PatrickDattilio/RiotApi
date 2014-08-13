package com.howbig.riot.api;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public interface ApiService {
    public static final String API_VERSION = "v1.2";
    public static final String API_LANGUAGE = "na";

    @GET("/static-data/{lang}/{apiVersion}/versions")
    public String[] getVersions(@Path("lang") String language, @Path("apiVersion") String apiVersion, @Query("api_key") String apiKey);
}
