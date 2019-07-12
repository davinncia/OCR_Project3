package com.davincia.lucasmahe.entrevoisins_pj3.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;

import java.util.ArrayList;
import java.util.List;

public class NeighboursViewModel extends ViewModel {

    private MutableLiveData<List<Neighbour>> mNeighbours = new MutableLiveData<>();
    public LiveData<List<Neighbour>> neighbours = mNeighbours;

    private MutableLiveData<List<Neighbour>> mFavoriteNeighbours = new MutableLiveData<>();
    public LiveData<List<Neighbour>> favorites = mFavoriteNeighbours;

    private NeighboursRepository mRepo;


    public void init(){
        mRepo = NeighboursRepository.getInstance();
        refreshNeighbour();
    }

    public void refreshNeighbour(){
        //Set value triggers the observer
        mNeighbours.setValue(mRepo.getNeighbours());
    }

    public void deleteNeighbour(Neighbour neighbour){
        mRepo.deleteNeighbour(neighbour);
        refreshNeighbour();
    }

    public void setToFavorite(Neighbour neighbour, boolean isFavorite){
        mRepo.setToFavorite(neighbour, isFavorite);
        refreshNeighbour();
    }

    public void refreshFavoriteNeighbours(){

        List<Neighbour> favoritesList = new ArrayList<>();

        for (Neighbour neighbour : mNeighbours.getValue()){
            if (neighbour.isFavorite()){
                favoritesList.add(neighbour);
            }
        }
        mFavoriteNeighbours.setValue(favoritesList);
    }
}
