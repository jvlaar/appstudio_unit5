package com.example.josvlaar.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        new MenuRequest(this).getMenu(this, category);

        ListView listView = findViewById(R.id.itemList);
        listView.setOnItemClickListener(new OnListItemClick());
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> items) {
        ListView itemList = findViewById(R.id.itemList);
        MenuItemAdapter adapter = new MenuItemAdapter(this, items);
        itemList.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {
        Log.d("DEBUG", "ERROR: " + message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    private class OnListItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);

            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("menuItem", menuItem);
            startActivity(intent);
        }
    }
}
