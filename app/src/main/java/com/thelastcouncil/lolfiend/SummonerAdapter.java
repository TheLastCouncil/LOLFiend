package com.thelastcouncil.lolfiend;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raff on 10/14/2014.
 */
public class SummonerAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Summoner> summoners;
    int layout;


    public SummonerAdapter(Context context, LayoutInflater li, int layout) {
        this.context = context;
        inflater = li;
        summoners = new ArrayList<Summoner>();
        this.layout = layout;
    }

    public void updateData(ArrayList<Summoner> summoners) {
        this.summoners = summoners;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return summoners.size();
    }

    @Override
    public Object getItem(int i) {
        return summoners.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(layout, null);

            //Create new holder summoner.
            holder = new ViewHolder();
            holder.tvSummonerName = (TextView) convertView.findViewById(R.id.tvResultSummonerName);
            holder.tvSummonerLevel = (TextView) convertView.findViewById(R.id.tvResultSummonerLevel);
            holder.tvSummonerTier = (TextView) convertView.findViewById(R.id.tvResultSummonerTier);
            holder.ivSummonerIcon = (ImageView) convertView.findViewById(R.id.ivResultSummonerIcon);

            //Store current holder summoner for future use.
            convertView.setTag(holder);
        } else {

            //Retrieve already existing holder summoner.
            holder = (ViewHolder) convertView.getTag();
        }

        //Retrieve current summoner's information
        Summoner summoner = summoners.get(position);

        holder.tvSummonerName.setText(summoner.getName());
        holder.tvSummonerLevel.setText("Level: " + summoner.getLevel());
        holder.tvSummonerTier.setText(summoner.getTier());

        if(!summoner.getTier().equalsIgnoreCase("unranked"))
            holder.tvSummonerTier.setText(holder.tvSummonerTier.getText() + " (" + summoner.getLP() + " LP)");

        RiotGamesAPI.logInfo("profileIconID: " + summoner.getProfileIconID());
        Picasso.with(context).load(RiotGamesAPI.getSummonerIconURL(summoner.getProfileIconID())).placeholder(R.drawable.default_icon).into(holder.ivSummonerIcon);

        return convertView;
    }

    private static class ViewHolder {
        public TextView tvSummonerName, tvSummonerLevel, tvSummonerTier;
        public ImageView ivSummonerIcon;
    }
}
