package com.davincia.lucasmahe.entrevoisins_pj3.di;

import com.davincia.lucasmahe.entrevoisins_pj3.data.service.DummyNeighbourApiService;
import com.davincia.lucasmahe.entrevoisins_pj3.data.service.NeighbourApiService;
import com.davincia.lucasmahe.entrevoisins_pj3.data.preferences.AppFavoritesPreferences;
import com.davincia.lucasmahe.entrevoisins_pj3.data.preferences.FavoritesSharedPreferences;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static NeighbourApiService service = new DummyNeighbourApiService();
    private static FavoritesSharedPreferences preferences = new AppFavoritesPreferences();

    /**
     * Get an instance on @{@link NeighbourApiService}
     * @return
     */
    public static NeighbourApiService getNeighbourApiService() {
        return service;
    }


    public static FavoritesSharedPreferences getFavoritesSharedPrefs(){
        return preferences;
    }

    /**
     * Get always a new instance on @{@link NeighbourApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static NeighbourApiService getNewInstanceApiService() {
        return new DummyNeighbourApiService();
    }
}

