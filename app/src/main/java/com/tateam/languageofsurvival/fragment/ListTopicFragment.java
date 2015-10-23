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



/*
    public class ListGuideAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<ListTopic> guideList;
        LayoutInflater inflater;


        public ListGuideAdapter(Context context, ArrayList< ListTopic>  guideList) {
            this.mContext = context;
            this. guideList =  guideList;
            inflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return  guideList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder mViewHolder;
            View v=convertView;
            if (convertView == null) {
                LayoutInflater layoutInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                v = layoutInflater.inflate(R.layout.list_topic_item, parent,false);
                mViewHolder = new MyViewHolder();
                v.setTag(mViewHolder);
            } else {
                mViewHolder = (MyViewHolder) v.getTag();
            }
            mViewHolder.textTopic= (TextView) v.findViewById(R.id.TextTopic);
           // Typeface myTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "the_conjuring.ttf");
           // mViewHolder.textTopic.setTypeface(myTypeFace);
            mViewHolder.textTopic.setTag(guideList.get(position));


            //mViewHolder.idText.setText(guideList.get(position).getType());

            mViewHolder.textTopic.setText(guideList.get(position).getListName());



            return v;

        }


        public class MyViewHolder {
            TextView textTopic;


        }
    }
    */

    public boolean enablefloatButton() {
        return true;
    }

}
