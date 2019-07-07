package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;
import com.davincia.lucasmahe.entrevoisins_pj3.utils.ItemClickSupport;
import com.davincia.lucasmahe.entrevoisins_pj3.viewmodels.NeighboursViewModel;

import java.util.List;


public class FavoritesFragment extends Fragment {


    private NeighboursViewModel mNeighbourViewModel;
    private NeighboursRepository mRepo;

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

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    //TODO: when neighbour deleted in main, should be updated

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepo = NeighboursRepository.getInstance();

        mAdapter = new MyNeighbourRecyclerViewAdapter();

        favoriteIds = mRepo.getFavoriteIds(getContext());

        //Listen view model
        mNeighbourViewModel = ViewModelProviders.of(this).get(NeighboursViewModel.class);

        mNeighbourViewModel.init();

        mNeighbourViewModel.getFavoriteNeighbours(favoriteIds).observe(this, new Observer<List<Neighbour>>() {
            @Override
            public void onChanged(List<Neighbour> neighbours) {
                Log.d("debuglog", "changed");
                mAdapter.setData(neighbours);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        favoriteIds = mRepo.getFavoriteIds(getContext());

        refreshData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        configureOnClickRecyclerView();

        mRecyclerView.setAdapter(mAdapter);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void refreshData(){
        //Generate the favorite neighbour list
        mNeighbours = mRepo.getFavoriteNeighbours(favoriteIds);
        //Triggers the observer
        mNeighbourViewModel.getFavoriteNeighbours(favoriteIds);
    }

    /**
     * Handles click on recyclerView's items
     */
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        //Start detail activity
                        Neighbour neighbour = mAdapter.getNeighbour(position);
                        startActivity(NeighbourDetailActivity.navigate(getContext(), neighbour));
                    }
                });
    }
}
