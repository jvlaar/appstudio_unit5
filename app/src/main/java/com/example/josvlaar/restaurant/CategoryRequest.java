package com.example.josvlaar.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback activity;

    public CategoryRequest(Context aContext) {
        context = aContext;
    }

    public void getCategories(Callback aActivity) {
        activity = aActivity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            ArrayList<String> categoryList = new ArrayList<String>();
            JSONArray categories = response.getJSONArray("categories");
            int len = categories.length();
            for (int i = 0; i < len; i++) {
                String category = categories.getString(i);
                categoryList.add(category);
            }
            activity.gotCategories(categoryList);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }
}
