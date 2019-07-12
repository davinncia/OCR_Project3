package com.davincia.lucasmahe.entrevoisins_pj3.data.service;

import android.util.Log;



import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
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
    public Neighbour getSpecificNeighbour(int id) {

        //Look for the position of the neighbour in list with id
        for (Neighbour neighbour : neighbours){
            if (neighbour.getId().equals(id)){
                return neighbour;
            }
        }
        throw new IllegalStateException("Incoherent state, no user found for id: " + id);
    }

    @Override
    public void setToFavorite(Neighbour neighbour, boolean isFavorite) {

        //Look for the position of the neighbour in list with id
        for (int i = 0; i < neighbours.size(); i++){
            if (neighbour.getId().equals(neighbours.get(i).getId())){
                neighbours.get(i).setFavorite(isFavorite);

                Log.d("debuglog", "setToFavorite: " + Arrays.toString(neighbours.toArray()));
            }
        }
    }
}

