package com.davincia.lucasmahe.entrevoisins_pj3.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;

import java.util.List;

public class NeighboursViewModel extends ViewModel {

    private MutableLiveData<List<Neighbour>> mNeighbours;
    private MutableLiveData<Neighbour> mSpecificNeighbour;
    private NeighboursRepository mRepo;

    public void init(){
        if (mNeighbours != null){
            return;
        }
        mRepo = NeighboursRepository.getInstance();
        mNeighbours = mRepo.getNeighbours();
    }

    public LiveData<List<Neighbour>> getNeighbours(){

        //Set value triggers the observer !!!
        mNeighbours.setValue(mRepo.getNeighbours().getValue());
        return mNeighbours;

    }

    public LiveData<Neighbour> getSpecificNeighbour(int id){

        if (mSpecificNeighbour == null){
            mSpecificNeighbour = new MutableLiveData<>();
        }
        mSpecificNeighbour.setValue(mRepo.getSpecificNeighbour(id));
        return mSpecificNeighbour;
    }

    public void deleteNeighbour(Neighbour neighbour){
        mRepo.deleteNeighbour(neighbour);
    }
}
