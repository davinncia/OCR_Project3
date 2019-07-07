package com.davincia.lucasmahe.entrevoisins_pj3.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;

import java.util.List;

public class NeighboursViewModel extends ViewModel {

    private MutableLiveData<List<Neighbour>> mNeighbours = new MutableLiveData<>();
    public LiveData<List<Neighbour>> neighbours = mNeighbours;

    private MutableLiveData<List<Neighbour>> mFavoriteNeighbours = new MutableLiveData<>();
    public LiveData<List<Neighbour>> favorites = mFavoriteNeighbours;

    private MutableLiveData<List<Integer>> mFavoriteIds = new MutableLiveData<>();

    private NeighboursRepository mRepo;


    public void init(){
        mRepo = NeighboursRepository.getInstance();
        getNeighbours();
    }

    public void getNeighbours(){
        //Set value triggers the observer
        mNeighbours.setValue(mRepo.getNeighbours());
    }

    public void deleteNeighbour(Neighbour neighbour){
        mRepo.deleteNeighbour(neighbour);

    }

    public LiveData<List<Neighbour>> getFavoriteNeighbours(List<Integer> ids){
        mFavoriteNeighbours.setValue(mRepo.getFavoriteNeighbours(ids));
        return mFavoriteNeighbours;
    }

    public void removeFromFavorites (int id, Context context){
        mRepo.removeFromFavorites(id, context);
    }
}
