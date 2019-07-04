package com.davincia.lucasmahe.entrevoisins_pj3.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }


    /**
     * {@inheritDoc}
     */

    @Override
    @NonNull
    public Neighbour getSpecificNeighbour(int id) {

        //Look for the position of the neighbour in list with id
        for (Neighbour neighbour : neighbours){
            if (neighbour.getId().equals(id)){
                return neighbour;
            }
        }
        throw new IllegalStateException("Incoherent state, no user found for id: " + id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours(List<Integer> ids) {
        List<Neighbour> favorites = new ArrayList<>();

        //We select the ids which are favorite
        for (int i = 0; i < neighbours.size(); i++) { //could also have used if neighbours.contains()...

            for (int id : ids) {
                if (neighbours.get(i).getId().equals(id)) {
                    favorites.add(neighbours.get(i));
                }
            }
        }
        return favorites;
    }
}

