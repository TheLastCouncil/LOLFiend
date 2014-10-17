package com.thelastcouncil.lolfiend;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Raff on 10/16/2014.
 */
public class ProfileActivity extends Activity{

    Summoner summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.summoner_profile);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ivProfileIcon = (ImageView) findViewById(R.id.ivProfileIcon);
        TextView tvProfileName = (TextView) findViewById(R.id.tvProfileName);
        TextView tvProfileLevel = (TextView) findViewById(R.id.tvProfileLevel);
        TextView tvProfileTier = (TextView) findViewById(R.id.tvProfileTier);
        TextView tvProfileLP = (TextView) findViewById(R.id.tvProfileLP);

        summoner = getIntent().getParcelableExtra("summoner");
        getActionBar().setTitle(summoner.getName());

        Picasso.with(this).load(RiotGamesAPI.getSummonerIconURL(summoner.getProfileIconID())).into(ivProfileIcon);
        tvProfileName.setText(summoner.getName());
        tvProfileLevel.setText("Level " + summoner.getLevel());
        tvProfileTier.setText(summoner.getTier());
        tvProfileLP.setText(summoner.getLP() + " League Points");

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
}
