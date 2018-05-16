package com.example.josvlaar.restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // retrieve the MenuItem from the intent
        Intent intent = getIntent();
        MenuItem menuItem = (MenuItem) intent.getSerializableExtra("menuItem");

        // find the text fields
        ImageView image = findViewById(R.id.imageView2);
        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        TextView description = findViewById(R.id.description);
        TextView category = findViewById(R.id.category);

        // set the text fields
        name.setText(menuItem.getName());
        price.setText("€ " + menuItem.getPrice());
        description.setText(menuItem.getDescription());
        category.setText(menuItem.getCategory());

        // set the image
        try {
            URL url = new URL(menuItem.getImageUrl());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            image.setImageBitmap(bmp);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
