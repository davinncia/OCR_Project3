package com.davincia.lucasmahe.entrevoisins_pj3.di;

import com.davincia.lucasmahe.entrevoisins_pj3.service.DummyNeighbourApiService;
import com.davincia.lucasmahe.entrevoisins_pj3.service.NeighbourApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static NeighbourApiService service = new DummyNeighbourApiService();

    /**
     * Get an instance on @{@link NeighbourApiService}
     * @return
     */
    public static NeighbourApiService getNeighbourApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link NeighbourApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static NeighbourApiService getNewInstanceApiService() {
        return new DummyNeighbourApiService();
    }
}

