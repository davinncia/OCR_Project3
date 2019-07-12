package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.viewmodels.NeighboursViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NeighbourDetailActivity extends AppCompatActivity {

    private static final String INTENT_ID = "INTENT_ID";
    public static final String KEY_NEIGHBOUR = "KEY_NEIGHBOUR";

    private ImageView avatarView;

    private NeighboursViewModel mNeighbourViewModel;

    private Neighbour mNeighbour;


    //UI
    private TextView titleView;
    private FloatingActionButton mFavoriteFab;
    private androidx.appcompat.widget.Toolbar toolbar;

    public static Intent navigate(Context context, Neighbour neighbour){
        Intent detailsIntent = new Intent(context, NeighbourDetailActivity.class);
        detailsIntent.putExtra(KEY_NEIGHBOUR, neighbour);
        return detailsIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        titleView = findViewById(R.id.textView_detail_title);
        mFavoriteFab = findViewById(R.id.fab_favorite);
        ImageButton backArrow = findViewById(R.id.image_details_back_arrow);

        mFavoriteFab.setOnClickListener(fabListener);
        backArrow.setOnClickListener(backListener);

        mNeighbour = getIntent().getParcelableExtra(KEY_NEIGHBOUR);

        mNeighbourViewModel = ViewModelProviders.of(this).get(NeighboursViewModel.class);
        mNeighbourViewModel.init();

        //UI
        if (mNeighbour != null) {
            initUi();
        }
    }


    ////////////////////////////////////
    ////////////////UI//////////////////
    ////////////////////////////////////
    private void initUi(){
        avatarView = findViewById(R.id.toolbarImage);
        TextView nameView = findViewById(R.id.textView_detail_name);
        TextView addressView = findViewById(R.id.textView_detail_address);
        TextView phoneView = findViewById(R.id.textView_detail_phone);
        TextView networkView = findViewById(R.id.textView_detail_network);
        TextView descriptionView = findViewById(R.id.textView_detail_description);


        if (mNeighbour.isFavorite()){
            mFavoriteFab.setImageResource(R.drawable.ic_star);
        } else  {
            mFavoriteFab.setImageResource(R.drawable.ic_star_empty);
        }

        String address = mNeighbour.getAddress();

        titleView.setText(mNeighbour.getName());
        titleView.setTextColor(Color.WHITE);
        nameView.setText(mNeighbour.getName());
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(avatarView);
        addressView.setText(address);
        phoneView.setText(mNeighbour.getPhone());
        networkView.setText(mNeighbour.getMail());
        descriptionView.setText(mNeighbour.getDescription());
    }

    ////////////////////////////////////
    ///////////////CLICKS///////////////
    ////////////////////////////////////
    View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (mNeighbour.isFavorite()){
                //Already in favorites
                mFavoriteFab.setImageResource(R.drawable.ic_star_empty);

                mNeighbourViewModel.setToFavorite(mNeighbour, false);
                mNeighbour.setFavorite(false);

            } else {
                //Not yet in favorites
                mFavoriteFab.setImageResource(R.drawable.ic_star);
                mNeighbourViewModel.setToFavorite(mNeighbour, true);
                mNeighbour.setFavorite(true);

            }

            Toast.makeText(NeighbourDetailActivity.this, "Favorite: " + mNeighbour.isFavorite(), Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}

