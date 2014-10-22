package com.thelastcouncil.lolfiend;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Raff on 10/20/2014.
 */
public class FavoritesActivity extends Activity {

    ListView lvFavoritesList;
    SummonerAdapter summonerAdapter;
    ArrayList summonerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list);

        lvFavoritesList = (ListView) findViewById(R.id.lvFavoritesList);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
