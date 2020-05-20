package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.FavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mFavoriteNeighbours;

    public MyFavoriteRecyclerViewAdapter(List<Neighbour> items) {
        mFavoriteNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mFavoriteNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FavoriteNeighbourEvent(neighbour));
            }
        });

        holder.intent.putExtra("neighbour_id", neighbour.getId());
    }

    @Override
    public int getItemCount() {
        return mFavoriteNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.favorites_item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.favorites_item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.favorites_item_list_cancel_button)
        public ImageButton mCancelButton;
        private Intent intent;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            intent = new Intent(view.getContext(), NeighbourActivity.class);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
