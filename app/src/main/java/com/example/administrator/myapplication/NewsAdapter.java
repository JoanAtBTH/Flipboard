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
 * Created by Administrator on 1/8/2017.
 */

public class NewsAdapter extends ArrayAdapter<ListModelNews> {
    private LayoutInflater layoutInflater;
    private final String LOG_TAG=NewsAdapter.class.getSimpleName();

    public NewsAdapter(Activity context, List<ListModelNews> data) {
        super(context, R.layout.lv_news_item, data);
        layoutInflater = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        Holder holder=null;
        if(convertView==null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.lv_news_item, parent, false);
            holder.setTextView((TextView) convertView
                    .findViewById(R.id.tvSubscribedSubcategory));
        }
        final ListModelNews modelNews= getItem(position);
        holder.getTextView().setText(modelNews.getTitle());
        holder.getTextView().setText(modelNews.getNewsContent());
        return convertView;
    }



    static class Holder {
        /*
         * private attributes
         */
        private TextView textView;

        /*
         * Getter & setters
         */
        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

    }

}
