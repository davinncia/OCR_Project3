package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.di.DI;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.service.NeighbourApiService;
import com.davincia.lucasmahe.entrevoisins_pj3.utils.ItemClickSupport;
import com.davincia.lucasmahe.entrevoisins_pj3.utils.SharedPreferencesFormat;

import java.util.List;


public class FavoritesFragment extends Fragment {

    private SharedPreferences sharedPref;
    private NeighbourApiService mApiService;

    private List<Integer> favoriteIds;
    private List<Neighbour> mNeighbours;

    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FavoritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getNeighbourApiService();
        fetchFavoriteIds();

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("debuglog", "top");
        fetchFavoriteIds();
        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        configureOnClickRecyclerView();

        initRecyclerView();

        //configureOnClickRecyclerView();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    //TODO: should this method be placed in its own repository ?
    private void fetchFavoriteIds(){
        //Initializing favorites from preferences
        sharedPref = getContext().getSharedPreferences(getString(R.string.SHARED_PREF_FAVORITES), Context.MODE_PRIVATE);
        favoriteIds = SharedPreferencesFormat.getPreferencesToArrayList(sharedPref.getString(getString(R.string.shared_pref_key), null));
    }

    private void initRecyclerView(){
        //Generate the favorite neighbour list
        mNeighbours = mApiService.getFavoriteNeighbours(favoriteIds);

        //Display it in recyclerView
        mAdapter = new MyNeighbourRecyclerViewAdapter(mNeighbours);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Handles click on recyclerView's items
     */
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        Integer id = mAdapter.getNeighbour(position).getId();

                        //Start detail activity
                        Intent detailsIntent = new Intent(getContext(), NeighbourDetailActivity.class);
                        detailsIntent.putExtra("ID", id);
                        startActivity(detailsIntent);
                    }
                });
    }
}
