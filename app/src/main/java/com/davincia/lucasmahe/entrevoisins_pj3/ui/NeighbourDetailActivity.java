package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;

import butterknife.BindView;

public class NeighbourDetailActivity extends AppCompatActivity {

    private String mNeighboorName;
    private Neighbour mNeighbour;

    //UI
    private TextView titleView;
    private TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        titleView = findViewById(R.id.textView_detail_title);
        nameView = findViewById(R.id.textView_detail_name);

        mNeighboorName = getIntent().getStringExtra("NAME");
        titleView.setText(mNeighboorName);

        //TODO: get the corespondant user
        getNeighbour(mNeighboorName);
    }

    private Neighbour getNeighbour(String name){
        return mNeighbour;
    }
}

