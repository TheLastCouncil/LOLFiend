package com.thelastcouncil.lolfiend;

/**
 * Created by Raff on 10/8/2014.
 */
public class Summoner {
    private int id;
    private String name;
    private int level;
    private String tier;
    private int lp;

    public Summoner(String name, int level, String tier, int lp) {
        this.name = name;
        this.level = level;
        this.tier = tier;
        this.lp = lp;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getTier() {
        return tier;
    }

    public void setTier (String tier) {
        this.tier = tier;
    }

    public int getLP() {
        return lp;
    }

    public void setLP(int lp) {
        this.lp = lp;
    }
}
