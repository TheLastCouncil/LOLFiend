package com.thelastcouncil.lolfiend;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by Raff on 10/18/2014.
 */
public class Match implements Parcelable{

    //Match information
    private long gameID;
    private long createDate;
    private int summonerSpell1;
    private int summonerSpell2;
    private String subType;

    //Match summoner information
    private boolean win;
    private int championID;
    private String championName;
    private int level,
                assists,
                kills,
                deaths,
                gold,
                minions;

    public Match (long gameID) {
        this.gameID = gameID;
        this.createDate = 0;
        this.summonerSpell1 = 0;
        this.summonerSpell2 = 0;
        this.subType = "Loading";
        this.win = true;
        this.championID = 0;
        this.championName = "";
        this.level = 0;
        this.assists = 0;
        this.kills = 0;
        this.deaths = 0;
        this.gold = 0;
        this.minions = 0;
    }

    public Match(Parcel parcel) {
        String[] data = new String[14];
        parcel.readStringArray(data);

        this.gameID = Long.parseLong(data[0]);
        this.createDate = Long.parseLong(data[1]);
        this.summonerSpell1 = Integer.parseInt(data[2]);
        this.summonerSpell2 = Integer.parseInt(data[3]);
        this.subType = data[4];
        this.win = Boolean.parseBoolean(data[5]);
        this.championID = Integer.parseInt(data[6]);
        this.championName = data[7];
        this.level = Integer.parseInt(data[8]);
        this.assists = Integer.parseInt(data[9]);
        this.kills = Integer.parseInt(data[10]);
        this.deaths = Integer.parseInt(data[11]);
        this.gold = Integer.parseInt(data[12]);
        this.minions = Integer.parseInt(data[13]);
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

    public String getChampionName() { return championName; }

    public void setChampionName(String championName) { this.championName = championName; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                String.valueOf(this.gameID),
                String.valueOf(createDate),
                String.valueOf(summonerSpell1),
                String.valueOf(summonerSpell2),
                subType,
                String.valueOf(win),
                String.valueOf(championID),
                championName,
                String.valueOf(level),
                String.valueOf(assists),
                String.valueOf(kills),
                String.valueOf(deaths),
                String.valueOf(gold),
                String.valueOf(minions)
        });
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {

        @Override
        public Match createFromParcel(Parcel parcel) {
            return new Match(parcel);
        }

        @Override
        public Match[] newArray(int i) {
            return new Match[i];
        }
    };
}
