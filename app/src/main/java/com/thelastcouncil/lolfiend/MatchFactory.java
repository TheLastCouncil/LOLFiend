package com.thelastcouncil.lolfiend;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Raff on 10/5/2014.
 */
public class MatchFactory {
    private Match match;

    public MatchFactory(JSONObject jsonObject) {
        JSONArray games = jsonObject.optJSONArray("games");
        JSONObject latestGame = games.optJSONObject(0);

        //Initializing the match object with the response information.
        if(latestGame != null) {
            RiotGamesAPI.logInfo("Match data found.");
            match = new Match(latestGame.optLong("gameId"));
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
            RiotGamesAPI.logInfo("Summoner Spell1: " + match.getSummonerSpell1());
            match.setSummonerSpell2(latestGame.optInt("spell2"));
            RiotGamesAPI.logInfo("SummonerSpell2: " + match.getSummonerSpell2());

            JSONObject stats = latestGame.optJSONObject("stats");
            match.setLevel(stats.optInt("level"));
            RiotGamesAPI.logInfo("Level: " + match.getLevel());
            match.setKills(stats.optInt("championsKilled"));
            RiotGamesAPI.logInfo("Kills: " + match.getKills());
            match.setDeaths(stats.optInt("numDeaths"));
            RiotGamesAPI.logInfo("Deaths: " + match.getDeaths());
            match.setAssists(stats.optInt("assists"));
            RiotGamesAPI.logInfo("Assists: " + match.getAssists());
            match.setGold(stats.optInt("goldEarned"));
            RiotGamesAPI.logInfo("Gold Earned: " + match.getGold());
            match.setMinions(stats.optInt("minionsKilled"));
            RiotGamesAPI.logInfo("Minions Killed: " + match.getMinions());
            match.setWin(stats.optBoolean("win"));
            RiotGamesAPI.logInfo("Win: " + match.isWin());
        }
    }


    public Match getMatch() {
        return this.match;
    }
}
