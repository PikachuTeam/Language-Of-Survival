package com.tateam.languageofsurvival.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.app.BaseFragment;
import com.tateam.languageofsurvival.entity.ListTopic;

import java.util.ArrayList;

import tatteam.com.app_common.ui.drawable.FlatEffectDrawable;


public class ListTopicFragment extends BaseFragment {

    public static final String ITEMSTRING = "item";
    private ArrayList<ListTopic> topicArrayList = new ArrayList<>();
    private CardView btnMeeting, btnDaily, btnDirection, btnHotel, btnRestaurant, btnShopping, btnSocializing;
    private CardView btnWeather;
    private FlatEffectDrawable flatMeeting, flatDaily, flatDirection, flatHotel, flatRestaurant, flatShopping, flatSocialzing, flatWeather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected boolean enableSpeak() {
        return false;
    }

    @Override
    protected boolean enableToolbar() {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        btnMeeting = (CardView) view.findViewById(R.id.buttonMeeting);
        btnDaily = (CardView) view.findViewById(R.id.buttonDaily);
        btnRestaurant = (CardView) view.findViewById(R.id.buttonRestaurant);
        btnDirection = (CardView) view.findViewById(R.id.buttonDirection);
        btnShopping = (CardView) view.findViewById(R.id.buttonShopping);
        btnHotel = (CardView) view.findViewById(R.id.buttonHotel);
        btnSocializing = (CardView) view.findViewById(R.id.buttonSocializing);
        btnWeather = (CardView) view.findViewById(R.id.buttonWeather);

        flatMeeting = (FlatEffectDrawable) view.findViewById(R.id.flatMeeting);
        flatSocialzing = (FlatEffectDrawable) view.findViewById(R.id.flatSocializing);
        flatShopping = (FlatEffectDrawable) view.findViewById(R.id.flatShopping);
        flatRestaurant = (FlatEffectDrawable) view.findViewById(R.id.flatRestaurant);
        flatDirection = (FlatEffectDrawable) view.findViewById(R.id.flatDirection);
        flatDaily = (FlatEffectDrawable) view.findViewById(R.id.flatDaily);
        flatWeather = (FlatEffectDrawable) view.findViewById(R.id.flatWeather);
        flatHotel = (FlatEffectDrawable) view.findViewById(R.id.flatHotel);

        flatMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "meeting");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(listFragment);
            }
        });
        flatRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "restaurant");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(listFragment);
            }
        });
        flatDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "daily");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(listFragment);
            }
        });
        flatDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "direction");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(listFragment);
            }
        });
        flatShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "shopping");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(listFragment);
            }
        });
        flatSocialzing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "socializing");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(listFragment);

            }
        });
        flatWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "weather");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(listFragment);
            }
        });
        flatHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "hotel");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(listFragment);
            }
        });
        return view;
    }

    public boolean enablefloatButton() {
        return true;
    }

}
