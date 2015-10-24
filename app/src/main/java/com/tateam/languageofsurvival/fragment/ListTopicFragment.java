package com.tateam.languageofsurvival.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.app.BaseFragment;
import com.tateam.languageofsurvival.database.DataSource;
import com.tateam.languageofsurvival.entity.ListTopic;

import java.util.ArrayList;


public class ListTopicFragment extends BaseFragment {
    private ListView lv;
    private ListGuideAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private ArrayList<ListTopic> topicArrayList =new ArrayList<>();
    private CardView btnMeeting,btnDaily,btnDirection, btnHotel, btnRestaurant, btnShopping,btnSocializing;
    private CardView btnWeather;
    public static final String MEETING="Meeting people - Rencontrer d'autres personnes";
    public static final String RESTAURANT="Restaurant";
    public static final String DAILYROUTINE="Daily routine - La routine quotidienne";
    public static final String ITEMSTRING="item";
   // public Bundle bundle =new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected boolean enableToolbar() {
        return true;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.content_main, container, false);
        View view = inflater.inflate(R.layout.test_main, container, false);

        //enableBackButton()
          //bundle=new Bundle();
       // lv = (ListView) view.findViewById(R.id.lvMain);
        getBaseActivity().getToolbar().setSubtitle("");
        btnMeeting= (CardView) view.findViewById(R.id.buttonMeeting);
        btnDaily=(CardView) view.findViewById(R.id.buttonDaily);
        btnRestaurant=(CardView) view.findViewById(R.id.buttonRestaurant);
        btnDirection=(CardView) view.findViewById(R.id.buttonDirection);
        btnShopping=(CardView) view.findViewById(R.id.buttonShopping);
        btnHotel=(CardView) view.findViewById(R.id.buttonHotel);
        btnSocializing=(CardView) view.findViewById(R.id.buttonSocializing);
        btnWeather=(CardView) view.findViewById(R.id.buttonWeather);
        btnMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(ITEMSTRING, "meeting");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
              //  replaceFragment(listFragment);
                replaceFragment(getActivity().getFragmentManager(), listFragment, "","");
            }
        });

        btnDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(ITEMSTRING, "daily");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
               // replaceFragment(listFragment);
                replaceFragment(getActivity().getFragmentManager(), listFragment, "","");
            }
        });

        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(ITEMSTRING, "restaurant");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
               // replaceFragment(listFragment);
                replaceFragment(getActivity().getFragmentManager(), listFragment, "","");
            }
        });

        btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(ITEMSTRING, "direction");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
               // replaceFragment(listFragment);
                replaceFragment(getActivity().getFragmentManager(), listFragment, "","");
            }
        });

        btnShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(ITEMSTRING, "shopping");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
               // replaceFragment(listFragment);
                replaceFragment(getActivity().getFragmentManager(), listFragment, "","");
            }
        });

        btnHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(ITEMSTRING, "hotel");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                //replaceFragment(listFragment);
                replaceFragment(getActivity().getFragmentManager(), listFragment, "","");
            }
        });

        btnSocializing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "socializing");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(getActivity().getFragmentManager(), listFragment, "","");

            }
        });

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ITEMSTRING, "weather");
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
               // replaceFragment(listFragment);
                replaceFragment(getActivity().getFragmentManager(), listFragment, "","");
            }
        });
        //bundle.putString(ITEMSTRING, "weather");

        /*
        LoadDataTopic();
        adapter = new ListGuideAdapter(getActivity(), topicArrayList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (topicArrayList.get(position).getListName()) {
                    case MEETING:
                        bundle.putString(ITEMSTRING, "meeting");
                    case RESTAURANT:
                        bundle.putString(ITEMSTRING, "restaurant");
                    case DAILYROUTINE:
                        bundle.putString(ITEMSTRING, "daily");
                }


                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                replaceFragment(listFragment);
            }
        });
*/
        return view;
    }
    //load data
    private void LoadDataTopic() {
        DataSource.getInstance().init(getActivity().getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
        topicArrayList = DataSource.getInstance().getList();
    }

    public boolean enablefloatButton() {
        return true;
    }

}
