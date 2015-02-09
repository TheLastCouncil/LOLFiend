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
public class MatchFactory {

    JSONObject jsonObject;
    Context context;
    int summonerId;
    Match match;

    public MatchFactory(Context context, int summonerId) {

        this.summonerId = summonerId;
        this.context = context;
    }

    public void setSummonerID(int id) {
        summonerId = id;
    }

    public Match getMatch() {
        retrieveMatchData();
        return this.match;
    }

    private void retrieveMatchData() {

        MainActivity.client.get(context, RiotGamesAPI.queryRecentGameInfo(summonerId, RiotGamesAPI.Region.REGION_NA), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                JSONArray games = response.optJSONArray("games");
                JSONObject latestGame = games.optJSONObject(0);

                //Initializing the match object with the response information.
                match = new Match(latestGame.optLong("gameId"));

                //TODO: summoner.setRecentMatch(match.getGameID()); after returning match!!
                RiotGamesAPI.logInfo("Game ID: " + match.getGameID());
                match.setCreateDate(latestGame.optLong("createDate"));
                RiotGamesAPI.logInfo("create Date: " + match.getCreateDate());
                match.setChampionID(latestGame.optInt("championId"));
                RiotGamesAPI.logInfo("Champion ID: " + match.getChampionID());
                if (latestGame.optString("subType").equalsIgnoreCase("none")) {
                    match.setSubType(latestGame.optString("gameMode"));
                } else {
                    match.setSubType(latestGame.optString("subType"));
                }
                RiotGamesAPI.logInfo("Sub Type: " + match.getSubType());
                match.setSummonerSpell1(latestGame.optInt("spell1"));
                match.setSummonerSpell2(latestGame.optInt("spell2"));

                JSONObject player = latestGame.optJSONObject("stats");
                match.setLevel(player.optInt("level"));
                match.setKills(player.optInt("championsKilled"));
                match.setDeaths(player.optInt("numDeaths"));
                match.setAssists(player.optInt("assists"));
                match.setGold(player.optInt("goldEarned"));
                match.setMinions(player.optInt("minionsKilled"));
                match.setWin(player.optBoolean("win"));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

}
