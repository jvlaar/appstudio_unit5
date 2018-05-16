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

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback activity;

    public MenuRequest(Context aContext) {
        context = aContext;
    }

    public void getMenu(Callback aActivity, String category) {
        activity = aActivity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?category=" + category, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            ArrayList<MenuItem> itemList = new ArrayList<MenuItem>();
            JSONArray items = response.getJSONArray("items");
            int len = items.length();
            for (int i = 0; i < len; i++) {
                JSONObject JSONItem = items.getJSONObject(i);
                MenuItem menuItem = new MenuItem();
                menuItem.setCategory(JSONItem.getString("category"));
                menuItem.setDescription(JSONItem.getString("description"));
                menuItem.setImageUrl(JSONItem.getString("image_url"));
                menuItem.setName(JSONItem.getString("name"));
                menuItem.setPrice(JSONItem.getInt("price"));
                itemList.add(menuItem);
            }
            activity.gotMenuItems(itemList);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> items);
        void gotMenuError(String message);
    }
}
