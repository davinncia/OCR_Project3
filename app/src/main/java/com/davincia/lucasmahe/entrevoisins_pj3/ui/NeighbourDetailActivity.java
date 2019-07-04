package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.davincia.lucasmahe.entrevoisins_pj3.viewmodels.NeighboursViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.di.DI;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.service.NeighbourApiService;
import com.davincia.lucasmahe.entrevoisins_pj3.utils.SharedPreferencesFormat;

import java.util.ArrayList;

public class NeighbourDetailActivity extends AppCompatActivity {

    private static final String INTENT_ID = "INTENT_ID";

    private SharedPreferences sharedPrefs;

    private Integer mId;
    private Neighbour mNeighbour;

    private NeighbourApiService mApiService;
    private NeighboursViewModel neighboursViewModel;

    private ArrayList<Integer> favorites = new ArrayList<>();

    //UI
    private TextView titleView;
    private FloatingActionButton mFavoriteFab;
    private androidx.appcompat.widget.Toolbar toolbar;

    public static Intent navigate(Context context, int id){
        Intent detailsIntent = new Intent(context, NeighbourDetailActivity.class);
        detailsIntent.putExtra(INTENT_ID, id);
        return detailsIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        //mApiService = DI.getNeighbourApiService();

        titleView = findViewById(R.id.textView_detail_title);
        mFavoriteFab = findViewById(R.id.fab_favorite);
        ImageButton backArrow = findViewById(R.id.image_details_back_arrow);

        mFavoriteFab.setOnClickListener(fabListener);
        backArrow.setOnClickListener(backListener);

        mId = getIntent().getIntExtra(INTENT_ID, 0);

        //Get the corresponding user
        mNeighbour = getNeighbour(mId);

        //Initializing favorites from preferences
        sharedPrefs = this.getSharedPreferences(getString(R.string.SHARED_PREF_FAVORITES), MODE_PRIVATE);
        favorites = SharedPreferencesFormat.getPreferencesToArrayList(sharedPrefs.getString(getString(R.string.shared_pref_key), null));

        //UI
        if (mNeighbour != null) {
            initUi();
        }
    }

    /**
     *
     * @param id of the neighbour clicked on
     * @return the corresponding Neighbour object by finding its name in the list
     */
    private Neighbour getNeighbour(Integer id){

        //return mApiService.getSpecificNeighbour(id);
        neighboursViewModel = ViewModelProviders.of(this).get(NeighboursViewModel.class);
        neighboursViewModel.init();

        //TODO: setting an observer doesn't seem necessary as neighbour stays fix
        return neighboursViewModel.getSpecificNeighbour(id).getValue();
    }

    ////////////////////////////////////
    ////////////////UI//////////////////
    ////////////////////////////////////
    private void initUi(){
        ImageView avatarView = findViewById(R.id.toolbarImage);
        TextView nameView = findViewById(R.id.textView_detail_name);
        TextView addressView = findViewById(R.id.textView_detail_address);
        TextView phoneView = findViewById(R.id.textView_detail_phone);
        TextView networkView = findViewById(R.id.textView_detail_network);
        TextView descriptionView = findViewById(R.id.textView_detail_description);

        if (favorites.contains(mNeighbour.getId())){
            mFavoriteFab.setImageResource(R.drawable.ic_star);
        } else {
            mFavoriteFab.setImageResource(R.drawable.ic_star_empty);
        }

        titleView.setText(mNeighbour.getName());
        titleView.setTextColor(Color.WHITE);
        nameView.setText(mNeighbour.getName());
        //The urls provided in the project seem to be failing...
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(avatarView);
        addressView.setText("Here the personal address");
        phoneView.setText("00.00.00.00.00.00");
        networkView.setText("Here social network infos");
        descriptionView.setText("Personal description");
    }

    ////////////////////////////////////
    ///////////////CLICKS///////////////
    ////////////////////////////////////
    View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (favorites.contains(mNeighbour.getId())){
                //Already in favorites
                mFavoriteFab.setImageResource(R.drawable.ic_star_empty);
                favorites.remove(mNeighbour.getId());
            } else {
                //Not yet in favorites
                mFavoriteFab.setImageResource(R.drawable.ic_star);
                favorites.add(mNeighbour.getId());
            }

            //Save IDs of favorites in preferences
            String preferences = SharedPreferencesFormat.prerareDataForPreferences(favorites);
            sharedPrefs.edit().putString(getString(R.string.shared_pref_key), preferences).apply();

            Toast.makeText(NeighbourDetailActivity.this, preferences, Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}

