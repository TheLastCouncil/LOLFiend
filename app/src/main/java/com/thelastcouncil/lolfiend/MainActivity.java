package com.thelastcouncil.lolfiend;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;


public class MainActivity extends Activity implements View.OnClickListener, TextView.OnKeyListener, TextWatcher {

    EditText etSearch;
    Button bSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the search TextView.
        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.setOnKeyListener(this);
        etSearch.addTextChangedListener(this);

        //Set up search button.
        bSearch = (Button) findViewById(R.id.bSearch);
        bSearch.setOnClickListener(this);
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

                String names = etSearch.getText().toString().replaceAll("\\s", "");

                //Remove current search bar's text and disable search button upon search.
                etSearch.setText("");
                bSearch.setEnabled(false);
                etSearch.setEnabled(false);
                Log.d("LOLFiend", "Query URL: " + RiotGamesAPI.querySummonerName(names, RiotGamesAPI.Region.REGION_NA));
                querySearch(RiotGamesAPI.querySummonerName(names, RiotGamesAPI.Region.REGION_NA));
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            Toast.makeText(getApplicationContext(),"Feature not implemented.", Toast.LENGTH_SHORT).show();

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

        //Retrieve a JSONArray of data.
        client.get(searchString, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                Toast.makeText(getApplicationContext(),"Query search was successful.", Toast.LENGTH_SHORT).show();
                Log.d("LOLFiend", response.toString());
                etSearch.setEnabled(true);
            }

            @Override
            public void onFailure(int statusCode, Throwable e, JSONObject errorResponse) {

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
}
