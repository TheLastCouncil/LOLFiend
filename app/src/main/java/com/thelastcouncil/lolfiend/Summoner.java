package com.thelastcouncil.lolfiend;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by Raff on 10/8/2014.
 */

public class Summoner extends SugarRecord<Summoner> implements Parcelable{
    private int id;
    private String region;
    private String name;
    private int profileIconID;
    private int level;
    private String league;
    private String tier;
    private int lp;
    private int wins;
    private boolean favorite;
    private long recentMatch;

    public Summoner () {
    }

    public Summoner(String name) {
        this.id = 0;
        this.name = name;
        this.level = 1;
        this.profileIconID = 0;
        this.league = "Unranked";
        this.tier = "Unranked";
        this.lp = 0;
        this.wins = 0;
        this.favorite = false;
        this.recentMatch = 0;
    }

    public Summoner (Parcel in) {
        String[] data = new String[11];
        in.readStringArray(data);

        this.id = Integer.parseInt(data[0]);
        this.region = data[1];
        this.name = data[2];
        this.level = Integer.parseInt(data[3]);
        this.profileIconID = Integer.parseInt(data[4]);
        this.league = data[5];
        this.tier = data[6];
        this.lp = Integer.parseInt(data[7]);
        this.wins = Integer.parseInt(data[8]);
        this.favorite = Boolean.parseBoolean(data[9]);
        this.recentMatch = Long.parseLong(data[10]);
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

    public boolean isFavorite() {return favorite;}

    public void setFavorite(boolean favorite) {this.favorite = favorite;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                String.valueOf(this.id),
                this.region,
                this.name,
                String.valueOf(this.level),
                String.valueOf(this.profileIconID),
                this.league,
                this.tier,
                String.valueOf(this.lp),
                String.valueOf(this.wins),
                String.valueOf(this.favorite),
                String.valueOf(this.recentMatch)
        });
    }

    public static final Creator<Summoner> CREATOR = new Creator<Summoner>() {

        @Override
        public Summoner createFromParcel(Parcel parcel) {
            return new Summoner(parcel);
        }

        @Override
        public Summoner[] newArray(int i) {
            return new Summoner[i];
        }
    };

    public long getRecentMatch() {
        return recentMatch;
    }

    public void setRecentMatch(long recentMatch) {
        this.recentMatch = recentMatch;
    }
}
