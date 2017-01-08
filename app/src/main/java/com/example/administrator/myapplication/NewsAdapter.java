package com.example.administrator.myapplication;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 1/8/2017.
 */

public class NewsAdapter extends ArrayAdapter<ListModelNews> {
    private LayoutInflater layoutInflater;
    private final String LOG_TAG = NewsAdapter.class.getSimpleName();

    public NewsAdapter(Activity context, List<ListModelNews> data) {
        super(context, R.layout.lv_news_item, data);
        layoutInflater = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        Holder holder=null;
        if(convertView==null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.lv_news_item, parent, false);
            holder.setTvTittle((TextView) convertView.findViewById(R.id.tvTittleNews));
            holder.setTvContent((TextView) convertView.findViewById(R.id.tvContentNews));
            holder.setIvImage((ImageView) convertView.findViewById(R.id.ivNews));
        }
        final ListModelNews modelNews = getItem(position);
        holder.getTvTittle().setText(modelNews.getTitle());
        holder.getTvContent().setText(modelNews.getNewsContent());
        holder.getIvImage().setImageBitmap(modelNews.getBitmap());
        return convertView;
    }



    static class Holder {
        /*
         * private attributes
         */
        private TextView tvTittle;
        private TextView tvContent;
        private ImageView ivImage;

        /*
         * Getter & setters
         */

        public TextView getTvTittle() {
            return tvTittle;
        }

        public void setTvTittle(TextView tvTittle) {
            this.tvTittle = tvTittle;
        }

        public TextView getTvContent() {
            return tvContent;
        }

        public void setTvContent(TextView tvContent) {
            this.tvContent = tvContent;
        }

        public ImageView getIvImage() {
            return ivImage;
        }

        public void setIvImage(ImageView ivImage) {
            this.ivImage = ivImage;
        }
    }

}
