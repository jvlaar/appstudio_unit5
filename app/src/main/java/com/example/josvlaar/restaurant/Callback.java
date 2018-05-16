package com.example.josvlaar.restaurant;

import java.util.ArrayList;

public interface Callback {
    void gotCategories(ArrayList<String> categories);
    void gotCategoriesError(String message);
}