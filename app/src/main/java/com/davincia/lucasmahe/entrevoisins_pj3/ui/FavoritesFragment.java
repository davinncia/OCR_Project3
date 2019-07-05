package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;
import com.davincia.lucasmahe.entrevoisins_pj3.utils.ItemClickSupport;

import java.util.List;


public class FavoritesFragment extends Fragment {


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

        favoriteIds = mRepo.getFavoriteIds(getContext());

    }

    @Override
    public void onResume() {
        super.onResume();

        favoriteIds = mRepo.getFavoriteIds(getContext());
        initRecyclerView();
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

        initRecyclerView();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initRecyclerView(){
        //Generate the favorite neighbour list
        mNeighbours = mRepo.getFavoriteNeighbours(favoriteIds);

        //Display it in recyclerView
        mAdapter = new MyNeighbourRecyclerViewAdapter();
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
                        startActivity(NeighbourDetailActivity.navigate(getContext(), id));
                    }
                });
    }
}
