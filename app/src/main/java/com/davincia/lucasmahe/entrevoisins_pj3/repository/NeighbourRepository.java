package com.davincia.lucasmahe.entrevoisins_pj3.repository;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.service.NeighbourApiService;

import java.util.ArrayList;
import java.util.List;

public class NeighbourRepository {

    private final NeighbourApiService apiService;

    public NeighbourRepository(NeighbourApiService apiService) {
        this.apiService = apiService;
    }

    public Neighbour getSpecificNeighbour(Integer id){
        //First we generate the list of neighbours
        List<Neighbour> neighbours = apiService.getNeighbours();

        //Then we look for the position of the neighbour in list with id
        for (Neighbour neighbour : neighbours){
            if (neighbour.getId().equals(id)){
                return neighbour;
            }
        }
        return null;
    }

    public List<Neighbour> getFavoriteNeighbours(List<Integer> Ids) {
        //First we generate full list of neighbours
        List<Neighbour> neighbours = apiService.getNeighbours();
        List<Neighbour> favorites = new ArrayList<>();

        //Then we select the ids which are favorite
        for (int i = 0; i < neighbours.size(); i++) { //could also have used if neighbours.contains()...

            for (int id : Ids) {
                if (neighbours.get(i).getId().equals(id)) {
                    favorites.add(neighbours.get(i));
                }
            }
        }
        return favorites;
    }
}
