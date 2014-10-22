package com.thelastcouncil.lolfiend;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Raff on 10/5/2014.
 */
public class RiotGamesAPI {

    public static String querySummonerName(String name, String region) {

        try {
            name = URLEncoder.encode(name.replaceAll("\\s", "").trim(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.d("LOLFiend", e.getMessage());
        }
        return "https://" + region + BASE_QUERY_URL + region + "/v1.4/summoner/by-name/" + name + API_KEY;
    }

    public static String querySummonerInfo(int id, String region) {

        return "https://" + region + BASE_QUERY_URL + region + "/v2.4/league/by-summoner/" + id + "/entry" + API_KEY;
    }

    public static String queryRecentGameInfo(int id, String region) {

        return "https://" + region + BASE_QUERY_URL + region + "/v1.3/game/by-summoner/" + id + "/recent" + API_KEY;
    }

    public static void logInfo(String string) {
        Log.d("LOL Fiend", string);
    }

    public static String getSummonerIconURL(int summonerIconID) {

        String imageURL = "http://ddragon.leagueoflegends.com/cdn/4.18.1/img/profileicon/" + summonerIconID + ".png";
        RiotGamesAPI.logInfo("imageURL: " + imageURL);
        return imageURL;
    }

    public static String getChampionIconURL(String championKey) {

        String imageURL = "http://ddragon.leagueoflegends.com/cdn/4.18.1/img/champion/" + championKey + ".png";
        RiotGamesAPI.logInfo("imageURL: " + imageURL);
        return imageURL;
    }

    public static String getSpellIconURL(String spellKey) {

        String imageURL = "http://ddragon.leagueoflegends.com/cdn/4.18.1/img/spell/" + spellKey + ".png";
        RiotGamesAPI.logInfo("imageURL: " + imageURL);
        return imageURL;
    }

    //Base query url
    public static final String BASE_QUERY_URL = ".api.pvp.net/api/lol/";
    //API Key
    public static final String API_KEY = "?api_key=1d1d396d-999b-49cd-b992-5f1515e42b81";

    public static String spellIDToName(int spellID) {

        switch (spellID) {

            case 1:
                return "SummonerBoost";
            case 2:
                return "SummonerClairvoyance";
            case 3:
                return "SummonerExhaust";
            case 4:
                return "SummonerFlash";
            case 6:
                return "SummonerHaste";
            case 7:
                return "SummonerHeal";
            case 10:
                return "SummonerRevive";
            case 11:
                return "SummonerSmite";
            case 12:
                return "SummonerTeleport";
            case 13:
                return "SummonerMana";
            case 14:
                return "SummonerDot";
            case 17:
                return "SummonerOdinGarrison";
            case 21:
                return "SummonerBarrier";
            default:
                return "SummonerDot";
        }
    }

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
