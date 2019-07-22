package com.davincia.lucasmahe.entrevoisins_pj3.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;

import java.util.List;

public class NeighboursViewModel extends ViewModel {

    private MutableLiveData<List<Neighbour>> mNeighbours = new MutableLiveData<>();
    public LiveData<List<Neighbour>> neighbours = mNeighbours;


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

    public LiveData<List<Neighbour>> getFavoritesNeighbours = Transformations.switchMap(neighbours, neighbourList ->
            mRepo.fetchFavorites(neighbourList));

}
