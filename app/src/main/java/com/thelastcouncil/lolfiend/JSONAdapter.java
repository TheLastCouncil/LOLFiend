package com.thelastcouncil.lolfiend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Raff on 10/5/2014.
 */
public class JSONAdapter extends BaseAdapter{

    Context context;
    LayoutInflater inflater;
    JSONArray jsonArray;

    public JSONAdapter(Context c, LayoutInflater li) {
        context = c;
        inflater = li;
        jsonArray = new JSONArray();
    }

    public void updateData(JSONArray ja) {
        jsonArray = ja;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        return jsonArray.optJSONObject(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        //Check if the view already exists
        //If so, don't inflate again
        if(view == null) {
            //Inflate custom search results XML layout.
            view = inflater.inflate(R.layout.search_results, null);

            //Create new holder with sub-views.
            holder = new ViewHolder();
            holder.tvResultSummonerName = (TextView) view.findViewById(R.id.tvResultSummonerName);
            holder.tvResultSummonerLevel = (TextView) view.findViewById(R.id.tvResultSummonerLevel);
            holder.tvResultSummonerRank = (TextView) view.findViewById(R.id.tvResultSummonerRank);
            holder.tvResultSummonerLP = (TextView) view.findViewById(R.id.tvResultSummonerLP);

            //store current holder for future use.
            view.setTag(holder);
        } else {

            //retrieve already existing holder.
            holder = (ViewHolder) view.getTag();
        }

        //Retrieve current summoner's information.
        JSONObject jsonObject = (JSONObject) getItem(i);

        //Retrieve summoner information.
        String summonerName = "Unavailable",
               summonerLevel = "Unavailable",
               summonerRank = "Unavailable",
               summonerLP = "Unavailable";

        if(jsonObject.has("name"))
            summonerName = jsonObject.optString("name");
        if(jsonObject.has("summonerLevel"))
            summonerLevel = "Level " + jsonObject.optString("summonerLevel");

        //Display string on the TextViews on the ListView.
        holder.tvResultSummonerName.setText(summonerName);
        holder.tvResultSummonerLevel.setText(summonerLevel);

        //TODO: /api/lol/<region>/v2.5/league/by-summoner/<summoner id>/entry

        return view;
    }

    private static class ViewHolder {
        public TextView tvResultSummonerName;
        public TextView tvResultSummonerLevel;
        public TextView tvResultSummonerRank;
        public TextView tvResultSummonerLP;
    }
}
