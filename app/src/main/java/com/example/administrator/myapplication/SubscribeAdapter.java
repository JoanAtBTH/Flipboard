package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by joancolom on 15/11/16.
 */

public class SubscribeAdapter extends ArrayAdapter<ListModelSubscribe> {

    /*
     * private variables of hte class
     */
    private Activity context;
    private ArrayList<ListModelSubscribe> data;

    public SubscribeAdapter(Activity context, ArrayList<ListModelSubscribe> data) {
        super(context, R.layout.lv_subscribe_item, data);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        int n = 0;
        if (data != null) {
            n = data.size();
        }
        return n;
    }

    @Override
    public ListModelSubscribe getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.lv_subscribe_item, null);

        // set values of the item
        TextView subcategoryName = (TextView)item.findViewById(R.id.tvSubscribedSubcategory);
        String name = getItem(position).getName();
        subcategoryName.setText(name);
        CheckBox subcategorySubsribed = (CheckBox)item.findViewById(R.id.cbSubscribedSubcategory);
        if (getItem(position).isSubscribed())
            subcategorySubsribed.setChecked(true);
        else
            subcategorySubsribed.setChecked(false);

        return item;
    }
}
