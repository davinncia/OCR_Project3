package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.events.DeleteNeighbourEvent;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.utils.ItemClickSupport;
import com.davincia.lucasmahe.entrevoisins_pj3.viewmodels.NeighboursViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class NeighbourFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter mAdapter;

    private NeighboursViewModel mNeighboursViewModel;

    private List<Neighbour> mNeighbours;


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNeighbours = new ArrayList<>();

        mAdapter = new MyNeighbourRecyclerViewAdapter();

        mNeighboursViewModel = ViewModelProviders.of(this).get(NeighboursViewModel.class);
        mNeighboursViewModel.init();

        mNeighboursViewModel.neighbours.observe(this, neighbours -> {

            mAdapter.setData(neighbours);

        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);

        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        mRecyclerView.setAdapter(mAdapter);

        configureOnClickRecyclerView();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {

        mNeighboursViewModel.deleteNeighbour(event.neighbour);

        //TODO:Shouldn't this be done automatically ?
        mAdapter.notifyDataSetChanged();

        //Alerting our view model changes have been made to launch the observer
        //mNeighbours = mNeighboursViewModel.getNeighbours().getValue();
    }

    /**
     * Handles click on recyclerView's items
     */
   private void configureOnClickRecyclerView(){
       ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour_item)
               .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                   @Override
                   public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                       Neighbour neighbour = mAdapter.getNeighbour(position);
                       //Start detail activity
                       startActivity(NeighbourDetailActivity.navigate(getContext(), neighbour));
                   }
               });
   }

}