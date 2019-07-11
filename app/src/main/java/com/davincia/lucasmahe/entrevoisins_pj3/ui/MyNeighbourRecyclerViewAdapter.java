package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.events.DeleteNeighbourEvent;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private List<Neighbour> mNeighbours;

    private OnNeighbourListener listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);


        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours != null? mNeighbours.size() : 0;
    }

    public void setData(List<Neighbour> neighbours){
        mNeighbours = neighbours;
        notifyDataSetChanged();
    }

    public Neighbour getNeighbour(int position){
        return this.mNeighbours.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            //TODO: Good practice ?
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onNeighbourClick(getAdapterPosition(), mNeighbourAvatar);
                }
            });
        }

    }

    public interface OnNeighbourListener{
        void onNeighbourClick(int position, ImageView avatar);
    }

    public void setOnNeighbourListener(OnNeighbourListener listener){
        this.listener = listener;
    }
}
