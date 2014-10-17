package com.thelastcouncil.lolfiend;

/**
 * Created by Raff on 10/8/2014.
 */

public class Summoner {
    private int id;
    private String region;
    private String name;
    private int profileIconID;
    private int level;
    private String league;
    private String tier;
    private int lp;
    private int wins;

    public Summoner(String name) {
        this.id = 0;
        this.name = name;
        this.level = 1;
        this.profileIconID = 0;
        this.league = "Unranked";
        this.tier = "Unranked";
        this.lp = 0;
        this.wins = 0;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public void setName(String name) { this.name = name; }

    public String getName() {
        return name;
    }

    public void setLevel(int level) { this.level = level; }

    public int getLevel() {
        return level;
    }

    public void setTier (String tier) {
        this.tier = tier;
    }

    public String getTier() { return tier; }

    public int getLP() {
        return lp;
    }

    public void setLP(int lp) {
        this.lp = lp;
    }

    public void setProfileIconID (int id) {
        this.profileIconID = id;
    }

    public int getProfileIconID() {
        return profileIconID;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
