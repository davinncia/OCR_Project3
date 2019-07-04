package com.davincia.lucasmahe.entrevoisins_pj3;


import com.davincia.lucasmahe.entrevoisins_pj3.di.DI;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.data.service.DummyNeighbourGenerator;
import com.davincia.lucasmahe.entrevoisins_pj3.data.service.NeighbourApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    //TODO: why not using Repository like in Pj 2 ?
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getSpecificNeighbours() {
        Neighbour neighbourToGet = service.getNeighbours().get(0);

        Neighbour neighbourGot = service.getSpecificNeighbour(1);

        assertTrue(neighbourGot.equals(neighbourToGet));
    }

    @Test
    public void getFavoritesNeighbours(){
        //Creating false favorite ids (can't access SharedPreferences here)
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(3);

        List<Neighbour> favorites = service.getFavoriteNeighbours(ids);

        assertTrue(favorites.get(0).getName().equals("Caroline"));
    }
}

