package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joancolom on 15/11/16.
 */

public class SubscribeAdapter extends ArrayAdapter<ListModelSubscribe> implements View.OnClickListener {

    /*
     * private variables of hte class
     */
    private LayoutInflater layoutInflater;
    private final String LOG_TAG = SubscribeAdapter.class.getSimpleName();

    /*
     * creators
     */
    public SubscribeAdapter(Activity context, List<ListModelSubscribe> data) {
        super(context, R.layout.lv_subscribe_item, data);
        layoutInflater = LayoutInflater.from(context);
    }

    /*
     * public methods
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.lv_subscribe_item, parent, false);
            holder.setTextView((TextView) convertView
                    .findViewById(R.id.tvSubscribedSubcategory));
            holder.setCheckBox((CheckBox) convertView
                    .findViewById(R.id.cbSubscribedSubcategory));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final ListModelSubscribe modelSubscribe = getItem(position);
        holder.getTextView().setText(modelSubscribe.getName());
        holder.getCheckBox().setTag(position);
        holder.getCheckBox().setChecked(modelSubscribe.isSubscribed());
        holder.getCheckBox().setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View view) {
        CheckBox checkBox = (CheckBox) view;
        int position = (Integer) view.getTag();
        getItem(position).setSubscribed(checkBox.isChecked());
        String subcategory = getItem(position).getName();
        boolean subscribed = checkBox.isChecked();
        /*String msg = this.getContext().toString() + " " + position;
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "Subcategory: " + subcategory + "\n\tSubscribed: " + subscribed);*/
        DBHelper dbHelper = DBHelper.getInstance(this.getContext());
        dbHelper.subscribe_to_subcategory(subcategory, subscribed);
    }

    static class Holder {
        /*
         * private attributes
         */
        private TextView textView;
        private CheckBox checkBox;

        /*
         * Getter & setters
         */
        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }
    }
}
