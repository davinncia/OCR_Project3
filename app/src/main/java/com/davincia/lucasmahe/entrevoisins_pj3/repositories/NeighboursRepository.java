package com.davincia.lucasmahe.entrevoisins_pj3.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.davincia.lucasmahe.entrevoisins_pj3.di.DI;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.data.service.NeighbourApiService;
import com.davincia.lucasmahe.entrevoisins_pj3.data.preferences.FavoritesSharedPreferences;

import java.util.List;

public class NeighboursRepository {

    private static NeighboursRepository instance;

    private FavoritesSharedPreferences mFavoritesPrefs;
    private NeighbourApiService __neighboursApiService;

    private NeighboursRepository(){
        //No constructor allowed
    }

    //singleton pattern
    public static NeighboursRepository getInstance(){
        if (instance == null){
            instance = new NeighboursRepository();
        }
        return instance;
    }

    private NeighbourApiService getNeighboursApiService(){
        if (__neighboursApiService == null){
            __neighboursApiService = DI.getNeighbourApiService();
        }
        return __neighboursApiService;
    }

    //Retrieving data form service
    public List<Neighbour> getNeighbours(){
        //inject our api service

        return getNeighboursApiService().getNeighbours();
    }

    public void deleteNeighbour(Neighbour neighbour){
        getNeighboursApiService().deleteNeighbour(neighbour);
        //TODO: remove also from pref
    }


    public List<Integer> getFavoriteIds(Context context){
        mFavoritesPrefs = DI.getFavoritesSharedPrefs();
        return mFavoritesPrefs.getFavoriteIds(context);
    }

    public List<Neighbour> getFavoriteNeighbours(List<Integer> ids){

        return getNeighboursApiService().getFavoriteNeighbours(ids);
    }

    public void saveFavorites(List<Integer> ids, Context context){

        mFavoritesPrefs.insertFavorites(ids, context);
    }

    public void removeFromFavorites(int id, Context context){
        mFavoritesPrefs.removeFromFavorites(id, context);
    }

}
