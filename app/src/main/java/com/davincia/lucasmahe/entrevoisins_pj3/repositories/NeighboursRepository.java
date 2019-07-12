package com.davincia.lucasmahe.entrevoisins_pj3.repositories;

import com.davincia.lucasmahe.entrevoisins_pj3.data.preferences.FavoritesSharedPreferences;
import com.davincia.lucasmahe.entrevoisins_pj3.data.service.NeighbourApiService;
import com.davincia.lucasmahe.entrevoisins_pj3.di.DI;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;

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
    }

    public void setToFavorite(Neighbour neighbour, boolean isFavorite){
        getNeighboursApiService().setToFavorite(neighbour, isFavorite);
    }

}
