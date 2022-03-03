package com.app.newsapp.utils;

import android.content.Context;

public class PreferenceUtils {

    private final Context context;

    public PreferenceUtils(Context context) {
        //no direct instances allowed. use di instead.
        this.context = context;
    }
}
