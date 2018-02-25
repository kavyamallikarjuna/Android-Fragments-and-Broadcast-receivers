package com.example.kavya.myproject3a2;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import static android.support.v7.widget.TintTypedArray.obtainStyledAttributes;

/**
 * Created by Kavya on 26-10-2017.
 */

public class AddImgAdp extends BaseAdapter{
    private Context context;
    private int itemBackground;
    private Integer[] Imgid;
    public AddImgAdp(Context c, Integer[] Imgid)
    {
        context = c;
        this.Imgid=Imgid;
        // sets a grey background; wraps around the images
        TypedArray a =c.obtainStyledAttributes(R.styleable.MyGallery);
        itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
        a.recycle();
    }
    // returns the number of images
    public int getCount() {
        return Imgid.length;
    }
    // returns the ID of an item
    public Object getItem(int position) {
        return position;
    }
    // returns the ID of an item
    public long getItemId(int position) {
        return position;
    }
    // returns an ImageView view
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(Imgid[position]);
        imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
        imageView.setBackgroundResource(itemBackground);
        return imageView;
    }

}
