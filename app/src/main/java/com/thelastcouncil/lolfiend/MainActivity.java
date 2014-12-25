package com.thelastcouncil.lolfiend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener, TextView.OnKeyListener, TextWatcher, AdapterView.OnItemClickListener {

    public static AsyncHttpClient client;
    public static SummonerAdapter summonerAdapter;
    public static ArrayList<Summoner> summonerList;

    EditText etSearch;
    Button bSearch;
    ListView lvSearchResults;
    SummonerFactory summonerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Add spinning progress bar.
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(false);

        //Setting initial content view to main XML.
        setContentView(R.layout.activity_main);

        //Instantiating client for search networking.
        client = new AsyncHttpClient();

        //Set up the search TextView.
        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.setOnKeyListener(this);
        etSearch.addTextChangedListener(this);

        //Set up search button.
        bSearch = (Button) findViewById(R.id.bSearch);
        bSearch.setOnClickListener(this);

        lvSearchResults = (ListView) findViewById(R.id.lvSearchResults);
        lvSearchResults.setOnItemClickListener(this);

        summonerList = new ArrayList<Summoner>();

        summonerAdapter = new SummonerAdapter(this, getLayoutInflater(), R.layout.search_results);

        lvSearchResults.setAdapter(summonerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.action_exit :
                return true;

            case R.id.action_favorites:
                Intent favoritesIntent = new Intent();
                favoritesIntent.setClass(this, FavoritesActivity.class);
                startActivity(favoritesIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.bSearch:
                Toast.makeText(getApplicationContext(),"Searching..", Toast.LENGTH_SHORT).show();

                String name = etSearch.getText().toString();

                //Remove current search bar's text and disable search button upon search.
                etSearch.setText("");
                bSearch.setEnabled(false);
                etSearch.setEnabled(false);
                querySearch(RiotGamesAPI.querySummonerName(name, RiotGamesAPI.Region.REGION_NA));
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

            String name = etSearch.getText().toString();

            //Remove current search bar's text and disable search button upon search.
            etSearch.setText("");
            bSearch.setEnabled(false);
            etSearch.setEnabled(false);
            querySearch(RiotGamesAPI.querySummonerName(name, RiotGamesAPI.Region.REGION_NA));
        }

        return false;
    }

    private void querySearch(String searchString) {

        setProgressBarIndeterminateVisibility(true);
        etSearch.setEnabled(false);

        //Retrieve a JSONObject of data.
        client.get(searchString, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(JSONObject response) {

                //Display Toast message.d
                Toast.makeText(getApplicationContext(),"Query search was successful.", Toast.LENGTH_SHORT).show();

                summonerFactory = new SummonerFactory(response);

                summonerList.add(summonerFactory.getSummoner());

                setProgressBarIndeterminateVisibility(false);
                etSearch.setEnabled(true);
            }

            @Override
            public void onFailure(int statusCode, Throwable e, JSONObject errorResponse) {

                //Display Toast message.
                switch (statusCode) {
                    case 404:
                        Toast.makeText(getApplicationContext(), "Summoner not found.", Toast.LENGTH_SHORT).show();
                        break;

                    case 429:
                        Toast.makeText(getApplicationContext(), "Rate limit exceeded.", Toast.LENGTH_SHORT).show();
                        break;

                    case 503:
                        Toast.makeText(getApplicationContext(), "Service is unavailable.", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "The search has failed.", Toast.LENGTH_SHORT).show();
                }

                etSearch.setEnabled(true);
                setProgressBarIndeterminateVisibility(false);
            }

        });
    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = etSearch.getText().toString().trim();

        if(text.equals(""))
            bSearch.setEnabled(false);
        else
            bSearch.setEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Summoner summoner = (Summoner) summonerAdapter.getItem(i);

        Intent profileIntent = new Intent(this, ProfileActivity.class);

        profileIntent.putExtra("summoner", summoner);

        startActivity(profileIntent);
    }
}
