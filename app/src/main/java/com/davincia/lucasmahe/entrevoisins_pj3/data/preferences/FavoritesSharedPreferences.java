package com.davincia.lucasmahe.entrevoisins_pj3.data.preferences;

import android.content.Context;

import java.util.List;

public interface FavoritesSharedPreferences {

    List<Integer> getFavoriteIds(Context context);

    void insertFavorites(List<Integer> ids, Context context);

    void removeFromFavorites(int id, Context context);
}
