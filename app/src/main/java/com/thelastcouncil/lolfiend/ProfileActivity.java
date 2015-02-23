package com.thelastcouncil.lolfiend;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by Raff on 10/16/2014.
 */
public class ProfileActivity extends Activity implements View.OnClickListener {

    Summoner summoner;
    Match latestMatch;

    MatchFactory matchFactory;

    LinearLayout llLatestBG;

    ImageView ivProfileIcon;
    TextView tvProfileName;
    TextView tvProfileLevel;
    TextView tvProfileTier;
    TextView tvProfileLP;

    ImageView ivChampionIcon;
    ImageView ivSummonerSpell1;
    ImageView ivSummonerSpell2;

    TextView tvMatchOutcome;
    TextView tvQueueType;
    TextView tvSummonerLevel;
    TextView tvKills;
    TextView tvDeaths;
    TextView tvAssists;
    TextView tvGold;
    TextView tvMinions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the content view to our summoner_profile.xml
        setContentView(R.layout.summoner_profile);

        //Enabling our "back" action bar button if the action bar exists.
        if(getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        //Retrieving the first views from the summoner_profile.xml
        ivProfileIcon = (ImageView) findViewById(R.id.ivProfileIcon);
        tvProfileName = (TextView) findViewById(R.id.tvProfileName);
        tvProfileLevel = (TextView) findViewById(R.id.tvProfileLevel);
        tvProfileTier = (TextView) findViewById(R.id.tvProfileTier);
        tvProfileLP = (TextView) findViewById(R.id.tvProfileLP);

        llLatestBG = (LinearLayout) findViewById(R.id.llLatestBG);

        ivChampionIcon = (ImageView) findViewById(R.id.ivLatestChampionIcon);
        ivSummonerSpell1 = (ImageView) findViewById(R.id.ivLatestSummonerSkill1);
        ivSummonerSpell2 = (ImageView) findViewById(R.id.ivLatestSummonerSkill2);

        tvMatchOutcome = (TextView) findViewById(R.id.tvLatestMatchOutcome);
        tvQueueType = (TextView) findViewById(R.id.tvLatestQueueType);
        tvSummonerLevel = (TextView) findViewById(R.id.tvLatestSummonerLevel);
        tvKills = (TextView) findViewById(R.id.tvLatestKills);
        tvDeaths = (TextView) findViewById(R.id.tvLatestDeaths);
        tvAssists = (TextView) findViewById(R.id.tvLatestAssists);
        tvGold = (TextView) findViewById(R.id.tvLatestGold);
        tvMinions = (TextView) findViewById(R.id.tvLatestMinions);

        //Retrieving the Parcelable summmoner object sent from the previous Intent.
        summoner = getIntent().getParcelableExtra("summoner");
        getActionBar().setTitle(summoner.getName());

        //Using Picasso to load the summoner icon once again.
        Picasso.with(this).load(RiotGamesAPI.getSummonerIconURL(summoner.getProfileIconID())).into(ivProfileIcon);
        tvProfileName.setText(summoner.getName());
        tvProfileLevel.setText("Level " + summoner.getLevel());
        tvProfileTier.setText(summoner.getTier());
        tvProfileLP.setText(summoner.getLP() + " League Points");

        //Method used to populate the recent match view.
        queryLatestMatch();
    }

    private void queryLatestMatch() {

        MainActivity.client.get(RiotGamesAPI.queryRecentGameInfo(summoner.getID(),RiotGamesAPI.Region.REGION_NA), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                matchFactory = new MatchFactory(response);
                latestMatch = matchFactory.getMatch();

                MainActivity.client.get(RiotGamesAPI.getChampionKey(latestMatch.getChampionID()), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        latestMatch.setChampionName(response.optString("key"));
                        populateLatestMatch(latestMatch);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        RiotGamesAPI.logInfo("No Champion ID found.");
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                RiotGamesAPI.logInfo("Unable to find recent match.");
            }
        });
    }

    private void populateLatestMatch(Match latestMatch) {
        RiotGamesAPI.logInfo("Champion Icon URL: " + RiotGamesAPI.getChampionIconURL(latestMatch.getChampionName()));
        Picasso.with(getApplicationContext()).load(RiotGamesAPI.getChampionIconURL(latestMatch.getChampionName())).into(ivChampionIcon);

        RiotGamesAPI.logInfo("Spell 1: " + RiotGamesAPI.getSpellIconURL(RiotGamesAPI.spellIDToName(latestMatch.getSummonerSpell1())));
        RiotGamesAPI.logInfo("Spell 2: " + RiotGamesAPI.getSpellIconURL(RiotGamesAPI.spellIDToName(latestMatch.getSummonerSpell2())));

        Picasso.with(getApplicationContext()).load(RiotGamesAPI.getSpellIconURL(RiotGamesAPI.spellIDToName(latestMatch.getSummonerSpell1()))).into(ivSummonerSpell1);
        Picasso.with(getApplicationContext()).load(RiotGamesAPI.getSpellIconURL(RiotGamesAPI.spellIDToName(latestMatch.getSummonerSpell2()))).into(ivSummonerSpell2);

        //Setting match outcome text depending on win boolean.
        if(latestMatch.isWin()) {
            tvMatchOutcome.setText("WIN");
            tvMatchOutcome.setTextColor(Color.parseColor("#FF86FF4A"));
            llLatestBG.setBackgroundColor(0xFF2B9C25);
        } else {
            tvMatchOutcome.setText("LOSS");
            tvMatchOutcome.setTextColor(Color.parseColor("#FFFF5C4A"));
            llLatestBG.setBackgroundColor(0xFFA31E0F);
        }

        if(latestMatch.getSubType().equalsIgnoreCase("BOT")) {
            tvQueueType.setText("BOT 5V5");
        } else if (latestMatch.getSubType().equalsIgnoreCase("BOT_3x3")) {
            tvQueueType.setText("BOT 3V3");
        } else if (latestMatch.getSubType().equalsIgnoreCase("NORMAL")) {
            tvQueueType.setText("NORMAL 5V5");
        } else if (latestMatch.getSubType().equalsIgnoreCase("NORMAL_3x3")) {
            tvQueueType.setText("NORMAL 3V3");
        } else if (latestMatch.getSubType().equalsIgnoreCase("RANKED_SOLO_5x5")) {
            tvQueueType.setText("RANKED 5V5");
        } else if (latestMatch.getSubType().equalsIgnoreCase("RANKED_PREMADE_3x3")) {
            tvQueueType.setText("RANKED 3V3");
        } else if (latestMatch.getSubType().equalsIgnoreCase("RANKED_PREMADE_5x5")) {
            tvQueueType.setText("RANKED 5V5");
        } else if (latestMatch.getSubType().equalsIgnoreCase("ODIN_UNRANKED")) {
            tvQueueType.setText("DOMINION 5V5");
        } else if (latestMatch.getSubType().equalsIgnoreCase("RANKED_TEAM_3x3")) {
            tvQueueType.setText("RANKED TEAM 3V3");
        } else if (latestMatch.getSubType().equalsIgnoreCase("RANKED_TEAM_5x5")) {
            tvQueueType.setText("RANKED TEAM 5V5");
        } else if (latestMatch.getSubType().equalsIgnoreCase("CAP_5x5")) {
            tvQueueType.setText("TEAM BUILDER 5V5");
        } else if (latestMatch.getSubType().equalsIgnoreCase("ARAM_UNRANKED_5x5")) {
            tvQueueType.setText("ARAM 5V5");
        } else if (latestMatch.getSubType().equalsIgnoreCase("ONEFORALL_5x5")) {
            tvQueueType.setText("ONE FOR ALL 5V5");
        } else if (latestMatch.getSubType().equalsIgnoreCase("FIRSTBLOOD_1x1")) {
            tvQueueType.setText("SNOWDOWN 1V1");
        } else if (latestMatch.getSubType().equalsIgnoreCase("FIRSTBLOOD_2x2")) {
            tvQueueType.setText("SNOWDOWN 2V2");
        } else if (latestMatch.getSubType().equalsIgnoreCase("SR_6x6")) {
            tvQueueType.setText("HEXAKILL 6V6");
        } else if (latestMatch.getSubType().equalsIgnoreCase("URF")) {
            tvQueueType.setText("ULTRA RAPID FIRE");
        } else if (latestMatch.getSubType().equalsIgnoreCase("URF_BOT")) {
            tvQueueType.setText("ULTRA RAPID FIRE BOT");
        } else if (latestMatch.getSubType().equalsIgnoreCase("NIGHTMARE_BOT")) {
            tvQueueType.setText("DOOM BOTS 5V5");
        } else {
            tvQueueType.setText(latestMatch.getSubType());
        }

        tvSummonerLevel.setText("Level " + latestMatch.getLevel());
        tvKills.setText(latestMatch.getKills() + " kills / ");
        tvDeaths.setText(latestMatch.getDeaths() + " deaths / ");
        tvAssists.setText(latestMatch.getAssists() + " asst");
        tvGold.setText(latestMatch.getGold() + " gold / ");
        tvMinions.setText(latestMatch.getMinions() + " minions");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            default:

        }
    }
}
