package com.example.josvlaar.restaurant;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter {

    ArrayList<MenuItem> menuItems;

    public MenuItemAdapter(Context context, ArrayList<MenuItem> array) {
        super(context, R.layout.menu_item, array);
        menuItems = array;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);
        ImageView image = convertView.findViewById(R.id.imageView);
        Log.d("DEBUG", name + "");
        Log.d("DEBUG",  price + "");
        Log.d("DEBUG", image + "");

        name.setText(menuItems.get(position).getName());
        price.setText("€ " + menuItems.get(position).getPrice());
        try {
            URL url = new URL(menuItems.get(position).getImageUrl());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            image.setImageBitmap(bmp);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
}

