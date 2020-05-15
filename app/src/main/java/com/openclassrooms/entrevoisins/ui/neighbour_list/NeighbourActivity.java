package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.FavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @BindView(R.id.neighbour_activity_avatar)
    ImageView mAvatarImg;
    @BindView(R.id.neighbour_activity_favorite_fab)
    FloatingActionButton mFavoriteBtn;
    @BindView(R.id.neighbour_activity_title_txt)
    TextView mTitleTxt;
    @BindView(R.id.neighbour_activity_name_txt)
    TextView mNameTxt;
    @BindView(R.id.neighbour_activity_address_txt)
    TextView mAddressTxt;
    @BindView(R.id.neighbour_activity_phone_txt)
    TextView mPhoneNumberTxt;
    @BindView(R.id.neighbour_activity_facebook_txt)
    TextView mFacebookTxt;
    @BindView(R.id.neighbour_activity_aboutMe_txt)
    TextView mAboutMeTextTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mApiService = DI.getNeighbourApiService();
        mNeighbour = mApiService.getNeighbourById(getIntent().getLongExtra("neighbour_id", 0));

        ButterKnife.bind(this);
        initUIComponents();
        initFavoriteButtonUI();

        mFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FavoriteNeighbourEvent(mNeighbour));
            }
        });
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
     * Init UI components using mNeighbour params
     */
    private void initUIComponents() {
        Glide.with(this)
                .load(mNeighbour.getAvatarUrl()
                        .replace("/150?", "/450?"))
                .centerCrop()
                .into(mAvatarImg);
        mTitleTxt.setText(mNeighbour.getName());
        mNameTxt.setText(mNeighbour.getName());
        mAddressTxt.setText(mNeighbour.getAddress());
        mPhoneNumberTxt.setText(mNeighbour.getPhoneNumber());
        mFacebookTxt.setText("www.facebook.fr/"+mNeighbour.getName());
        mAboutMeTextTxt.setText(mNeighbour.getAboutMe());
    }

    /**
     * Init favorite button UI based on favorite status of current neighbour
     */
    private void initFavoriteButtonUI() {
        if (mNeighbour.isFavorite())
            mFavoriteBtn.setImageResource(R.drawable.ic_star_yellow_24dp);
        else
            mFavoriteBtn.setImageResource(R.drawable.ic_star_grey_24dp);
    }

    /**
     * Fired if the user clicks on the favorite button
     * @param event
     */
    @Subscribe
    public void onFavoriteNeighbour(FavoriteNeighbourEvent event) {
        initFavoriteButtonUI();
    }
}