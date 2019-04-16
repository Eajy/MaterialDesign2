package com.eajy.materialdesign2.fragment;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eajy.materialdesign2.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zhang on 2019.04.02.
 */
public class CardsFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private CardView card__1, card__2, card__3;
    private ImageView img_card_1, img_card_2;
    private Button btn_card_1_action1, btn_card_1_action2, btn_card_3_action_1, btn_card_3_action_2;
    private AdView ad_view_card;
    private CardView card_ad_card;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_cards, container, false);
        card__1 = nestedScrollView.findViewById(R.id.card_1);
        card__2 = nestedScrollView.findViewById(R.id.card_2);
        card__3 = nestedScrollView.findViewById(R.id.card_3);
        img_card_1 = nestedScrollView.findViewById(R.id.img_card_1);
        img_card_2 = nestedScrollView.findViewById(R.id.img_card_2);
        btn_card_1_action1 = nestedScrollView.findViewById(R.id.btn_card_1_action1);
        btn_card_1_action2 = nestedScrollView.findViewById(R.id.btn_card_1_action2);
        btn_card_3_action_1 = nestedScrollView.findViewById(R.id.btn_card_3_action_1);
        btn_card_3_action_2 = nestedScrollView.findViewById(R.id.btn_card_3_action_2);
        ad_view_card = nestedScrollView.findViewById(R.id.ad_view_card);
        card_ad_card = nestedScrollView.findViewById(R.id.card_ad_card);
        return nestedScrollView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_card_1_action1.setOnClickListener(this);
        btn_card_1_action2.setOnClickListener(this);
        btn_card_3_action_1.setOnClickListener(this);
        btn_card_3_action_2.setOnClickListener(this);
        card__1.setOnTouchListener(this);
        card__2.setOnTouchListener(this);
        card__3.setOnTouchListener(this);
        Glide.with(getContext()).load(R.drawable.material_design_5).apply(new RequestOptions().fitCenter()).into(img_card_1);
        Glide.with(getContext()).load(R.drawable.material_design_4).apply(new RequestOptions().fitCenter()).into(img_card_2);
        showAd();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_card_1_action1:
                Snackbar.make(view, getString(R.string.main_flat_button_1_clicked), Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btn_card_1_action2:
                Snackbar.make(view, getString(R.string.main_flat_button_2_clicked), Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btn_card_3_action_1:
                Snackbar.make(view, getString(R.string.main_flat_button_2_clicked), Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btn_card_3_action_2:
                Snackbar.make(view, getString(R.string.main_flat_button_1_clicked), Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ObjectAnimator downAnimator = ObjectAnimator.ofFloat(view, "translationZ", 16);
                downAnimator.setDuration(200);
                downAnimator.setInterpolator(new DecelerateInterpolator());
                downAnimator.start();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                ObjectAnimator upAnimator = ObjectAnimator.ofFloat(view, "translationZ", 0);
                upAnimator.setDuration(200);
                upAnimator.setInterpolator(new AccelerateInterpolator());
                upAnimator.start();
                break;
        }
        return false;
    }

    private void showAd() {
        try {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("app", MODE_PRIVATE);
            if (!sharedPreferences.getBoolean("isDonated", false)) {
                AdRequest adRequest = new AdRequest.Builder().build();
                ad_view_card.loadAd(adRequest);

                Animation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(500);
                card_ad_card.setVisibility(View.VISIBLE);
                card_ad_card.startAnimation(animation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
