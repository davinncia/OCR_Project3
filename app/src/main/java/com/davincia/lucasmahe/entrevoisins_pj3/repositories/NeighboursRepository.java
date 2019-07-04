package com.davincia.lucasmahe.entrevoisins_pj3.repositories;

import androidx.lifecycle.MutableLiveData;

import com.davincia.lucasmahe.entrevoisins_pj3.di.DI;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.service.NeighbourApiService;

import java.util.List;

public class NeighboursRepository {

    private static NeighboursRepository instance;
    private NeighbourApiService mApiService;

    //singleton pattern
    public static NeighboursRepository getInstance(){
        if (instance == null){
            instance = new NeighboursRepository();
        }
        return instance;
    }

    //TODO: Should we use LiveData from here ? before or just in viewmodel ?
    //Retrieving data form service
    public MutableLiveData<List<Neighbour>> getNeighbours(){
        //inject our api service
        mApiService = DI.getNeighbourApiService();

        //return a LiveData object
        MutableLiveData<List<Neighbour>> data = new MutableLiveData<>();
        data.setValue(mApiService.getNeighbours());

        return data;
    }

    //Retrieving a neighbour from its id
    public Neighbour getSpecificNeighbour(int id){
        //inject our api service
        mApiService = DI.getNeighbourApiService();
        return mApiService.getSpecificNeighbour(id);
    }

    public void deleteNeighbour(Neighbour neighbour){
        mApiService.deleteNeighbour(neighbour);
    }
}
