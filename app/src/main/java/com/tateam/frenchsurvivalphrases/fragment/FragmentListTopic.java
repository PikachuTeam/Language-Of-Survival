package com.tateam.frenchsurvivalphrases.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;
import com.tateam.frenchsurvivalphrases.database.DataSource;
import com.tateam.frenchsurvivalphrases.entity.ListTopic;
import com.tateam.frenchsurvivalphrases.utility.FragmentWebView;

import java.util.ArrayList;


public class FragmentListTopic extends BaseFragment {
    private ListView lv;
    private ListGuideAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private ArrayList<ListTopic> topicArrayList =new ArrayList<>();

    public static final String MEETING="Meeting people - Rencontrer d'autres personnes";
    public static final String RESTAURANT="Restaurant";
    public static final String DAILYROUTINE="Daily routine - La routine quotidienne";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    protected boolean enableBackButton() {
        return true;
    }
    @Override
    protected boolean enableToolbar() {
        return true;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_topic, container, false);
        //enableBackButton()
        lv = (ListView) view.findViewById(R.id.lvMain);
        LoadDataTopic();
        adapter = new ListGuideAdapter(getActivity(), topicArrayList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(topicArrayList.get(position).getListName().compareTo(MEETING)==0){
                   replaceFragment(new FragmentMeeting());
               }
                else if(topicArrayList.get(position).getListName().compareTo(RESTAURANT)==0){
                   replaceFragment(new FragmentRestaurant());
               }
               else if(topicArrayList.get(position).getListName().compareTo(DAILYROUTINE)==0){
                   replaceFragment(new FragmentDaily());
               }
            }
        });
        floatingActionButton= (FloatingActionButton) view.findViewById(R.id.fabHistory);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FragmentRecent());
            }
        });
        return view;
    }
    //load data
    private void LoadDataTopic() {
        DataSource.getInstance().init(getActivity().getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
        topicArrayList = DataSource.getInstance().getList();
    }


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
            Typeface myTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "the_conjuring.ttf");
            mViewHolder.textTopic.setTypeface(myTypeFace);
            mViewHolder.textTopic.setTag(guideList.get(position));


            //mViewHolder.idText.setText(guideList.get(position).getType());

            mViewHolder.textTopic.setText(guideList.get(position).getListName());



            return v;

        }


        public class MyViewHolder {
            TextView textTopic;


        }
    }

}
