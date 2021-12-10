package com.minhien.testapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 78;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if(view == null){
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(175,270));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        else{
            imageView = (ImageView)view;
        }
        imageView.setImageResource(R.drawable.bb);
        return imageView;
    }
}
