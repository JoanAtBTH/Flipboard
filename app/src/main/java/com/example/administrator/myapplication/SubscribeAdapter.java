package com.example.administrator.myapplication;

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

public class SubscribeAdapter extends BaseAdapter {

    /*
     * private variables of hte class
     */
    private Context context;
    private ArrayList<?> data;

    public SubscribeAdapter(Context context, ArrayList<?> data) {
        //super(context, R.layout.lv_subscribe_item, data);
        this.context = context;
        this.data = data;
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
    public Object getItem(int position) {
        return (Object)data.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListModelSubscribe modelSubscribe = (ListModelSubscribe) getItem(position);
        View item = convertView;
        if (item == null) {
            item = LayoutInflater.from(context).inflate(R.layout.lv_subscribe_item, null);
            //convertView = inflater.inflate(R.layout.lv_subscribe_item, parent, false);
        }

        // set values of the item
        TextView subcategoryName = (TextView)item.findViewById(R.id.tvSubscribedSubcategory);
        String name = modelSubscribe.getName();
        subcategoryName.setText(name);
        CheckBox subcategorySubsribed = (CheckBox)item.findViewById(R.id.cbSubscribedSubcategory);
        if (modelSubscribe.isSubscribed())
            subcategorySubsribed.setChecked(true);
        else
            subcategorySubsribed.setChecked(false);

        return item;
    }
}
