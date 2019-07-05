package com.davincia.lucasmahe.entrevoisins_pj3.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;

import java.util.List;

public class NeighboursViewModel extends ViewModel {

    private MutableLiveData<List<Neighbour>> mNeighbours = new MutableLiveData<>();
    public LiveData<List<Neighbour>> neighbours = mNeighbours;

    private MutableLiveData<Neighbour> mSpecificNeighbour = new MutableLiveData<>();
    public LiveData<Neighbour> specificNeighbour = mSpecificNeighbour;

    private NeighboursRepository mRepo;

    public void init(){
        mRepo = NeighboursRepository.getInstance();
        getNeighbours();
    }

    public void getNeighbours(){

        //Set value triggers the observer
        mNeighbours.setValue(mRepo.getNeighbours());
    }

    public void getSpecificNeighbour(int id){

        mSpecificNeighbour.setValue(mRepo.getSpecificNeighbour(id));

    }

    public void deleteNeighbour(Neighbour neighbour){
        mRepo.deleteNeighbour(neighbour);
    }
}
