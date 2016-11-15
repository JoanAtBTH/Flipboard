package com.example.administrator.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by joancolom on 15/11/16.
 */

public class AgenciesAdapter extends ArrayAdapter {

    /*
     * private variables of hte class
     */
    private Context context;
    private ArrayList data;

    public AgenciesAdapter(Context context, ArrayList data) {
        super(context, R.layout.lv_agency_item, data);
        this.context = context;
        this.data = data;
    }

    /*
    @Override
    public void View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.lv_agency_item, null);

        // set values of the item
        TextView agencyName = (TextView)item.findViewById(R.id.tvAgency);
        agencyName.setText(data.get(position).get);
    }
    */
}
