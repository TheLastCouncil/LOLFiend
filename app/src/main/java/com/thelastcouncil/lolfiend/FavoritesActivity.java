package com.thelastcouncil.lolfiend;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raff on 10/20/2014.
 */
public class FavoritesActivity extends Activity {

    ListView lvFavoritesList;
    SummonerAdapter summonerAdapter;
    ArrayList<Summoner> summonerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        lvFavoritesList = (ListView) findViewById(R.id.lvFavoritesList);

        summonerAdapter = new SummonerAdapter(this, getLayoutInflater(), R.layout.favorites_list_item);

        summonerList = new ArrayList<Summoner>();

        populateList();
    }

    private void populateList() {
        List<Summoner> summoners = Summoner.listAll(Summoner.class);

        if (summoners.isEmpty()) {
            Toast.makeText(this, "Your favorites list is empty.", Toast.LENGTH_LONG).show();
        } else {
            summonerList.clear();
            for (int i = 0; i < summoners.size(); i++)
                summonerList.addAll(summoners);
        }

        summonerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
