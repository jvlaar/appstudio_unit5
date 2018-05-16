package com.example.josvlaar.restaurant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoryRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        new CategoryRequest(this).getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Log.d("DEBUG", "I successfully received categories!");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.category_list_item, categories);
        ListView listView = findViewById(R.id.categoryList);
        listView.setAdapter(adapter);
       listView.setOnItemClickListener(new onListItemClick());
    }

    @Override
    public void gotCategoriesError(String message) {
        Log.d("DEBUG", "ERROR: " + message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    private class onListItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
