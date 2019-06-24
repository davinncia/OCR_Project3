package com.davincia.lucasmahe.entrevoisins_pj3.repository;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.service.NeighbourApiService;

import java.util.List;

public class NeighbourRepository {

    private final NeighbourApiService apiService;

    public NeighbourRepository(NeighbourApiService apiService) {
        this.apiService = apiService;
    }

    public Neighbour getSpecificNeighbour(String name){
        //First we generate the list of neighbours
        List<Neighbour> neighbours = apiService.getNeighbours();

        //Then we look for the position of the neighbour in list
        //note! this works fine in this case, but if two neighbours have the same name then it will fail
        //TODO: use ID !!!
        for (Neighbour neighbour : neighbours){
            if (neighbour.getName().equals(name)){
                return neighbour;
            }
        }
        return null;
    }
}
