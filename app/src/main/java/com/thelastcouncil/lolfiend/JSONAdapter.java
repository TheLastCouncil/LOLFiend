package com.thelastcouncil.lolfiend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;

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
            holder.name =

        }

        return view;
    }

    private static class ViewHolder {
        public TextView tvResultSummonerName;
        public TextView tvResultSummonerLevel;
        public TextView tvResultSummonerRank;
        public TextView tvResultSummonerLP;
    }
}
