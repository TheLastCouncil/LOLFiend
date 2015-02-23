package com.thelastcouncil.lolfiend;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Raff on 10/5/2014.
 */
public class SummonerFactory {

    Summoner summoner;

    public SummonerFactory(JSONObject jsonObject) {

        JSONArray array = jsonObject.names();

        JSONObject object = jsonObject.optJSONObject(array.optString(0));

        summoner = new Summoner(object.optString("name"));

        summoner.setID(object.optInt("id"));
        RiotGamesAPI.logInfo("ID: " + summoner.getID());

        summoner.setLevel(object.optInt("summonerLevel"));
        RiotGamesAPI.logInfo("Level: " + summoner.getLevel());

        summoner.setProfileIconID(object.optInt("profileIconId"));
        RiotGamesAPI.logInfo("Profile Icon ID: " + summoner.getProfileIconID());
    }

    public Summoner getSummoner() {
        return summoner;
    }

    //Adds detailed information to the Summoner object.
    public void setExtraData(JSONObject jsonObject) {

                JSONArray jsonArray = jsonObject.optJSONArray("" + summoner.getID());

                JSONObject summonerObject = jsonArray.optJSONObject(0);

                JSONArray entriesArray = summonerObject.optJSONArray("entries");

                JSONObject entries = entriesArray.optJSONObject(0);

                summoner.setTier(summonerObject.optString("tier") + " " + entries.optString("division"));
                summoner.setLP(entries.optInt("leaguePoints"));
                summoner.setWins(entries.optInt("wins"));

                RiotGamesAPI.logInfo("Tier: " + summoner.getTier());
                RiotGamesAPI.logInfo("League Points: " + summoner.getLP());
                RiotGamesAPI.logInfo("Wins: " + summoner.getWins());
    }
}
