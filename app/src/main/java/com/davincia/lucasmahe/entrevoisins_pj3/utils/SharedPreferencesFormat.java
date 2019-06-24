package com.davincia.lucasmahe.entrevoisins_pj3.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesFormat {

    public static String prerareDataForPreferences(List<Integer> idList){
        StringBuilder builder = new StringBuilder();

        for (int id : idList){
            builder.append(String.valueOf(id));
            builder.append("-");
        }

        return builder.toString();
    }

    public static ArrayList<Integer> getPreferencesToArrayList(String preferences){
        ArrayList<Integer> favorites = new ArrayList<>();

        if (preferences == null){
            return favorites;
        }

        String[] preferencesSplit = preferences.split("-");

        for (String pref : preferencesSplit){
            favorites.add(Integer.parseInt(pref));
        }

        return favorites;
    }
}
