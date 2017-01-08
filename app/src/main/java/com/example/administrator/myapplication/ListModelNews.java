package com.example.administrator.myapplication;

/**
 * Created by Administrator on 1/8/2017.
 */
import android.graphics.Bitmap;
import android.widget.TextView;

public class ListModelNews {
    private String title;
    private String newsContent;
    private Bitmap bitmap;

    public ListModelNews() {}

    public ListModelNews(String title, String newsContent) {
        this.title = title;
        this.newsContent = newsContent;
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
