package com.davincia.lucasmahe.entrevoisins_pj3.service;

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

    //TODO: is here the right place ?
    /**
     * {@inheritDoc}
     */
    @Override
    public Neighbour getSpecificNeighbour(Integer id) {

        //Look for the position of the neighbour in list with id
        for (Neighbour neighbour : neighbours){
            if (neighbour.getId().equals(id)){
                return neighbour;
            }
        }
        return null;
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

