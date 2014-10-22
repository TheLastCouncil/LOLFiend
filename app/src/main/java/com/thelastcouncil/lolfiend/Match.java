package com.thelastcouncil.lolfiend;

/**
 * Created by Raff on 10/18/2014.
 */
public class Match {

    //Match information
    private long gameID;
    private long createDate;
    private int summonerSpell1;
    private int summonerSpell2;
    private String subType;

    //Match summoner information
    private boolean win;
    private int championID,
                level,
                assists,
                kills,
                deaths,
                gold,
                minions;

    public Match () {
        this.gameID = 0;
        this.createDate = 0;
        this.summonerSpell1 = 0;
        this.summonerSpell2 = 0;
        this.subType = "Loading";
        this.win = true;
        this.championID = 0;
        this.level = 0;
        this.assists = 0;
        this.kills = 0;
        this.deaths = 0;
        this.gold = 0;
        this.minions = 0;
    }

    public long getGameID() {
        return gameID;
    }

    public void setGameID(long gameID) {
        this.gameID = gameID;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public int getSummonerSpell1() {
        return summonerSpell1;
    }

    public void setSummonerSpell1(int summonerSpell1) {
        this.summonerSpell1 = summonerSpell1;
    }

    public int getSummonerSpell2() {
        return summonerSpell2;
    }

    public void setSummonerSpell2(int summonerSpell2) {
        this.summonerSpell2 = summonerSpell2;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMinions() {
        return minions;
    }

    public void setMinions(int minions) {
        this.minions = minions;
    }

    public int getChampionID() {
        return championID;
    }

    public void setChampionID(int championID) {
        this.championID = championID;
    }
}
