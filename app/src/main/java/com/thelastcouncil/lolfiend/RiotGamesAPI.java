package com.thelastcouncil.lolfiend;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Raff on 10/5/2014.
 */
public class RiotGamesAPI {

    public static String querySummonerName(String name, String region) {

        try {
            name = URLEncoder.encode(name.replaceAll("\\s", ""), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.d("LOLFiend", e.getMessage());
        }
        return "https://" + region + BASE_QUERY_URL + region + "/v1.4/summoner/by-name/" + name + API_KEY;
    }

    public static String querySummonerInfo(int id, String region) {

        return "https://" + region + BASE_QUERY_URL + region + "/v2.4/league/by-summoner/" + id + "/entry" + API_KEY;
    }

    public static void logInfo(String string) {
        Log.d("LOL Fiend", string);
    }

    public static String getSummonerIconURL(int summonerIconID) {

        String imageURL = "http://ddragon.leagueoflegends.com/cdn/4.18.1/img/profileicon/" + summonerIconID + ".png";
        RiotGamesAPI.logInfo("imageURL: " + imageURL);
        return imageURL;
    }

    //Base query url
    private static final String BASE_QUERY_URL = ".api.pvp.net/api/lol/";
    //API Key
    private static final String API_KEY = "?api_key=1d1d396d-999b-49cd-b992-5f1515e42b81";

    //Regions
    public class Region {
        public static final String REGION_BR = "br";
        public static final String REGION_EUNE = "eune";
        public static final String REGION_EUW = "euw";
        public static final String REGION_KR = "kr";
        public static final String REGION_LAN = "lan";
        public static final String REGION_LAS = "las";
        public static final String REGION_NA = "na";
        public static final String REGION_OCE = "oce";
        public static final String REGION_PBE = "pbe";
        public static final String REGION_RU = "ru";
        public static final String REGION_TR = "tr";
    }
}
