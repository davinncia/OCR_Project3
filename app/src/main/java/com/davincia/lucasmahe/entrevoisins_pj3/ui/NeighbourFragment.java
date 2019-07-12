package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.events.DeleteNeighbourEvent;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;
import com.davincia.lucasmahe.entrevoisins_pj3.ui.MyNeighbourRecyclerViewAdapter.OnNeighbourListener;
import com.davincia.lucasmahe.entrevoisins_pj3.viewmodels.NeighboursViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class NeighbourFragment extends Fragment implements OnNeighbourListener {

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
        mAdapter.setOnNeighbourListener(this);

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

    }

    /**
     * Handles click on recyclerView's items
     */
    @Override
    public void onNeighbourClick(int position, ImageView avatar) {
        Neighbour neighbour = mAdapter.getNeighbour(position);
        //Shared element transition
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(
                getActivity(), avatar, ViewCompat.getTransitionName(avatar)).toBundle();
        //Start detail activity
        startActivity(NeighbourDetailActivity.navigate(getContext(), neighbour), bundle);
    }
}