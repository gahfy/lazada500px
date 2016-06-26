package net.gahfy.lazada500px.utils;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Utils about objects that can be instancied without dependency.
 * @author Gaëtan HERFRAY
 */
public class Provider {
    /** The Singleton retrofit instance */
    private static Retrofit retrofit;

    /** The Singleton Gson instance */
    private static Gson gson;

    /** Base URL of the api */
    private static final String API_BASE_URL = "https://api.500px.com/v1/";

    /**
     * Returns the retrofit instance of the application.
     * @return the retrofit instance of the application
     */
    public static Retrofit getRetrofit(){
        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Returns the Gson instance of the application.
     * @return the Gson instance of the application
     */
    public static Gson getGson(){
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }
}
