package com.dragosneagu.emojinutrition;

import static com.dragosneagu.emojinutrition.Constants.FIRST_COLUMN;
import static com.dragosneagu.emojinutrition.Constants.SECOND_COLUMN;
import static com.dragosneagu.emojinutrition.Constants.THIRD_COLUMN;
import static com.dragosneagu.emojinutrition.Constants.FOURTH_COLUMN;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by dragosneagu on 03/12/2016.
 */

public class ListViewAdapter extends BaseAdapter {
    public ArrayList<HashMap> list;
    Activity activity;

    public ListViewAdapter(ArrayList<HashMap> list, Activity activity) {
        super();
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
        TextView txtFourth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_feed, null);
            holder = new ViewHolder();
            holder.txtFirst = (TextView) convertView.findViewById(R.id.FirstColumn);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.SecondColumn);
            holder.txtThird = (TextView) convertView.findViewById(R.id.ThirdColumn);
            holder.txtFourth = (TextView) convertView.findViewById(R.id.FourthColumn);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        HashMap map = list.get(position);
        holder.txtFirst.setText(map.get(FIRST_COLUMN).toString());
        holder.txtSecond.setText(map.get(SECOND_COLUMN).toString());
        holder.txtThird.setText(map.get(THIRD_COLUMN).toString());
        holder.txtFourth.setText(map.get(FOURTH_COLUMN).toString());
        return convertView;
    }

}