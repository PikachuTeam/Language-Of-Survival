package com.tateam.frenchsurvivalphrases.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.adapter.GuideAdapter;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;
import com.tateam.frenchsurvivalphrases.database.DataSource;
import com.tateam.frenchsurvivalphrases.entity.EnglishGuide;

import java.util.ArrayList;


public class RecentFragment extends BaseFragment implements GuideAdapter.clickListener {

    private ListView lv;
    private ListGuideAdapter meetingAdapter;
    private ArrayList<EnglishGuide> englishGuideArrayList =new ArrayList<>();
    public static final String KEY_DETAIL="KEYDETAIL";
    public View view;
    public int position=2;
    public String[] inforTransfer;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private GuideAdapter guideAdapter;
    protected boolean enableBackButton() {
        return true;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public boolean enablefloatButton() {
        return true;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recent, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler_english_recent);
        layoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerView.setLayoutManager(layoutManager);

        guideAdapter=new GuideAdapter(getBaseActivity(),englishGuideArrayList,1);
        LoadDataGuide();
        recyclerView.setAdapter(guideAdapter);
        guideAdapter.setmListener(this);

        /*
       // floatingActionButton= (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDataRecent();
            }
        });


        lv = (ListView) view.findViewById(R.id.lvMeeting);
        LoadDataRecent();
        meetingAdapter= new ListGuideAdapter(getActivity(), englishGuideArrayList);
        meetingAdapter.notifyDataSetChanged();
        lv.setAdapter(meetingAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Intent intent=new Intent(this,getBaseActivity().replace);

            }
        });
        */
        return view;
    }
    /*
    private void LoadDataRecent(){
        DataSource.getInstance().init(getActivity().getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
        englishGuideArrayList = DataSource.getInstance().getListRecent();

    }
    */
    private void LoadDataGuide() {
        DataSource.getInstance().init(getActivity().getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
        Bundle bundle = this.getArguments();



        // englishGuideArrayList = DataSource.getInstance().getListRecent(1);
    }
    @Override
    public void onPhraseClick(int position) {
        Bundle bundle = new Bundle();
        // bundle.putString(KEY_DETAIL, englishGuideArrayList.get(position).getFrenchSentence());
        inforTransfer=new String[]{englishGuideArrayList.get(position).getEnglishSentence(),
                englishGuideArrayList.get(position).getFrenchSentence()
        };
        bundle.putStringArray(KEY_DETAIL, inforTransfer);
        ViewdetailFragment viewdetailFragment = new ViewdetailFragment();
        viewdetailFragment.setArguments(bundle);

        replaceFragment(getActivity().getFragmentManager(), viewdetailFragment, englishGuideArrayList.get(position).getFrenchSentence(), englishGuideArrayList.get(position).getFrenchSentence());
    }
}
