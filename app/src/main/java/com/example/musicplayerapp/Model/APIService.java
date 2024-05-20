package com.example.musicplayerapp.Model;

public class APIService {
    private static String base_url = "https://highfive-app.000webhostapp.com/Server/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
