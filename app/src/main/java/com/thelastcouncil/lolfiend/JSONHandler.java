package com.thelastcouncil.lolfiend;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Raff on 10/5/2014.
 */
public class JSONHandler {

    JSONArray jsonArray;
    Summoner summoner;

    public JSONHandler(JSONObject jsonObject) {
        jsonArray = jsonObject.optJSONArray();
    }

    //Retrieve the summoner name for JSONArray conversion.
    private void calculateName(JSONObject jsonObject) {
        JSONArray ja = jsonObject.names();
        //TODO: continue with mapped pseudocode..
    }

    private void convertToSumoner() {
        jsonObject.opt
    }
}
