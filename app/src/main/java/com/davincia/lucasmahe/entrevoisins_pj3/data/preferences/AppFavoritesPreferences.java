package com.davincia.lucasmahe.entrevoisins_pj3.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.davincia.lucasmahe.entrevoisins_pj3.utils.SharedPreferencesFormat;

import java.util.List;

public class AppFavoritesPreferences implements FavoritesSharedPreferences {

    private SharedPreferences sharedPreferences;

    public static final String SHARED_PREF_FILE = "SHARED_PREF_FAVORITES";
    public static final String SHARED_PREF_KEY = "SHARED_PREF_KEY";

    @Override
    public List<Integer> getFavoriteIds(Context context) {

        if (sharedPreferences == null) {
            getFavoritesSharedPref(context);
        }

        List<Integer> ids = SharedPreferencesFormat.getPreferencesToArrayList(sharedPreferences.getString(SHARED_PREF_KEY, null));
        return ids;
    }

    @Override
    public void insertFavorites(List<Integer> ids, Context context) {

        if (sharedPreferences == null) {
            getFavoritesSharedPref(context);
        }

        String formattedIds = SharedPreferencesFormat.prerareDataForPreferences(ids);
        sharedPreferences.edit().putString(SHARED_PREF_KEY, formattedIds).apply();

    }

    private void getFavoritesSharedPref(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
    }
}
