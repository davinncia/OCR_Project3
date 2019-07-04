package com.davincia.lucasmahe.entrevoisins_pj3.data.service;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;

import java.util.List;

/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Get a Neighbour given its id
     * @param id
     */
    Neighbour getSpecificNeighbour(int id);

    /**
     * Get the list of favorites neighbours given their ids
     * @param ids
     */
    List<Neighbour> getFavoriteNeighbours(List<Integer> ids);
}
