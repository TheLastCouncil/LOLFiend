package com.thelastcouncil.lolfiend;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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


public class MainActivity extends Activity implements View.OnClickListener, TextView.OnKeyListener, TextWatcher, AdapterView.OnItemClickListener {

    EditText etSearch;
    Button bSearch;
    ListView lvSearchResults;
    JSONHandler jsonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Add spinning progress bar.
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(false);

        //Setting initial content view to main XML.
        setContentView(R.layout.activity_main);

        //Set up the search TextView.
        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.setOnKeyListener(this);
        etSearch.addTextChangedListener(this);

        //Set up search button.
        bSearch = (Button) findViewById(R.id.bSearch);
        bSearch.setOnClickListener(this);

        lvSearchResults = (ListView) findViewById(R.id.lvSearchResults);
        lvSearchResults.setOnItemClickListener(this);

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
        if (id == R.id.action_exit) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.bSearch:
                Toast.makeText(getApplicationContext(),"Searching..", Toast.LENGTH_SHORT).show();

                String names = etSearch.getText().toString();

                //Remove current search bar's text and disable search button upon search.
                etSearch.setText("");
                bSearch.setEnabled(false);
                etSearch.setEnabled(false);
                //Log.d("LOLFiend", "Query URL: " + RiotGamesAPI.querySummonerName(names, RiotGamesAPI.Region.REGION_NA));
                querySearch(RiotGamesAPI.querySummonerName(names, RiotGamesAPI.Region.REGION_NA));
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

            String names = etSearch.getText().toString();

            //Remove current search bar's text and disable search button upon search.
            etSearch.setText("");
            bSearch.setEnabled(false);
            etSearch.setEnabled(false);
            //Log.d("LOLFiend", "Query URL: " + RiotGamesAPI.querySummonerName(names, RiotGamesAPI.Region.REGION_NA));
            querySearch(RiotGamesAPI.querySummonerName(names, RiotGamesAPI.Region.REGION_NA));
        }

        return false;
    }

    private void querySearch(String searchString) {

        //Instantiating client for search networking.
        AsyncHttpClient client = new AsyncHttpClient();

        //Make the progress bar visible
        setProgressBarIndeterminateVisibility(true);

        //Retrieve a JSONObject of data.
        client.get(searchString, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(JSONObject response) {

                //Make the progress bar invisible
                setProgressBarIndeterminateVisibility(false);

                //Display Toast message.d
                Toast.makeText(getApplicationContext(),"Query search was successful.", Toast.LENGTH_SHORT).show();
                Log.d("LOLFiend", response.toString());
                etSearch.setEnabled(true);

                //TODO: inputting the JSONARRAY from query.
            }

            @Override
            public void onFailure(int statusCode, Throwable e, JSONObject errorResponse) {

                //Make the progress bar invisible
                setProgressBarIndeterminateVisibility(false);

                //Display Toast message.
                Toast.makeText(getApplicationContext(), "The search has failed.", Toast.LENGTH_LONG).show();
                Log.d("LOLFiend", e.getMessage());
                etSearch.setEnabled(true);
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

    }
}
