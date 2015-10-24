package com.tateam.languageofsurvival.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.adapter.GuideAdapter;
import com.tateam.languageofsurvival.app.BaseFragment;
import com.tateam.languageofsurvival.database.DataSource;
import com.tateam.languageofsurvival.entity.EnglishGuide;

import java.util.ArrayList;


public class ListFragment extends BaseFragment implements GuideAdapter.clickListener {
    private ListView lv;
    private ListGuideAdapter meetingAdapter;
    private ArrayList<EnglishGuide> englishGuideArrayList =new ArrayList<>();
    public static final String KEY_DETAIL="KEYDETAIL";
    public View view;
    public String inforDetail;
    public String[] inforTransfer;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private GuideAdapter guideAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    protected boolean enableBackButton() {
        return true;
    }
    public boolean enablefloatButton() {
        return true;
    }
    public boolean enableTitle(){
        return false;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_list, container, false);
       // lv = (ListView) v.findViewById(R.id.lvItem);

        meetingAdapter= new ListGuideAdapter(getActivity(), englishGuideArrayList);
        recyclerView= (RecyclerView) v.findViewById(R.id.recycler_english);
        layoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerView.setLayoutManager(layoutManager);
       LoadDataGuide();
        recyclerView.setAdapter(guideAdapter);
        guideAdapter.setmListener(this);

/*
        lv.setAdapter(meetingAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inforTransfer=new String[]{englishGuideArrayList.get(position).getEnglishSentence(),
                        englishGuideArrayList.get(position).getFrenchSentence()
                };
                //DataSource.getInstance().updateRecent(englishGuideArrayList.get(position).getEnglishSentence(),englishGuideArrayList.get(position).getRecent());
                int i=DataSource.getInstance().updateRecenthere(englishGuideArrayList.get(position).getEnglishSentence(),englishGuideArrayList.get(position).getRecent());

                Bundle bundle = new Bundle();
               // bundle.putString(KEY_DETAIL, englishGuideArrayList.get(position).getFrenchSentence());
                bundle.putStringArray(KEY_DETAIL, inforTransfer);

                ViewdetailFragment viewdetailFragment = new ViewdetailFragment();
                viewdetailFragment.setArguments(bundle);
                //   DataSource.getInstance().updateRecent(englishGuideArrayList.get(position).getEnglishSentence());
                replaceFragment(getActivity().getFragmentManager(), viewdetailFragment, englishGuideArrayList.get(position).getFrenchSentence(), englishGuideArrayList.get(position).getFrenchSentence());
            }
        });
        */
        return v;

        //lvItem
    }
    private void LoadDataGuide() {
        DataSource.getInstance().init(getActivity().getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
        Bundle bundle = this.getArguments();
        inforDetail = bundle.getString("item");

            switch (inforDetail){
                case "meeting":
                   // getBaseActivity().getToolbar().setTitle("Meeting");
                    getBaseActivity().getToolbar().setSubtitle(R.string.meeting);
                    englishGuideArrayList = DataSource.getInstance().getListLesson(1);
                    guideAdapter=new GuideAdapter(getBaseActivity(),englishGuideArrayList,1);
                    break;
                case "restaurant":
                    getBaseActivity().getToolbar().setSubtitle(R.string.restaurant);
                    englishGuideArrayList= DataSource.getInstance().getListLesson(2);
                    guideAdapter=new GuideAdapter(getBaseActivity(),englishGuideArrayList,2);
                    break;
                case "daily":
                    getBaseActivity().getToolbar().setSubtitle(R.string.daily);
                    englishGuideArrayList= DataSource.getInstance().getListLesson(3);
                    guideAdapter=new GuideAdapter(getBaseActivity(),englishGuideArrayList,3);
                    break;
                case "socializing":
                    getBaseActivity().getToolbar().setSubtitle(R.string.socializing);
                    englishGuideArrayList= DataSource.getInstance().getListLesson(4);
                    guideAdapter=new GuideAdapter(getBaseActivity(),englishGuideArrayList,4);
                    break;
                case  "shopping":
                    getBaseActivity().getToolbar().setSubtitle(R.string.shopping);
                    englishGuideArrayList= DataSource.getInstance().getListLesson(5);
                    guideAdapter=new GuideAdapter(getBaseActivity(),englishGuideArrayList,5);
                    break;
                case  "hotel":
                    getBaseActivity().getToolbar().setSubtitle(R.string.hotel);
                    englishGuideArrayList= DataSource.getInstance().getListLesson(6);
                    guideAdapter=new GuideAdapter(getBaseActivity(),englishGuideArrayList,6);
                    break;
                case  "direction":
                    getBaseActivity().getToolbar().setSubtitle(R.string.direction);
                    englishGuideArrayList= DataSource.getInstance().getListLesson(7);
                    guideAdapter=new GuideAdapter(getBaseActivity(),englishGuideArrayList,7);
                    break;
                case  "weather":
                    getBaseActivity().getToolbar().setSubtitle(R.string.weather);
                   englishGuideArrayList= DataSource.getInstance().getListLesson(8);
                    guideAdapter=new GuideAdapter(getBaseActivity(),englishGuideArrayList,8);
                break;
            }




        // englishGuideArrayList = DataSource.getInstance().getListRecent(1);
    }

    @Override
    public void onPhraseClick(int position) {
        inforTransfer=new String[]{englishGuideArrayList.get(position).getEnglishSentence(),
                englishGuideArrayList.get(position).getFrenchSentence()
        };
        //DataSource.getInstance().updateRecent(englishGuideArrayList.get(position).getEnglishSentence(),englishGuideArrayList.get(position).getRecent());
        int i=DataSource.getInstance().updateRecenthere(englishGuideArrayList.get(position).getEnglishSentence(),englishGuideArrayList.get(position).getRecent());

        Bundle bundle = new Bundle();
        // bundle.putString(KEY_DETAIL, englishGuideArrayList.get(position).getFrenchSentence());
        bundle.putStringArray(KEY_DETAIL, inforTransfer);

        ViewdetailFragment viewdetailFragment = new ViewdetailFragment();
        viewdetailFragment.setArguments(bundle);
        //   DataSource.getInstance().updateRecent(englishGuideArrayList.get(position).getEnglishSentence());
        replaceFragment(getActivity().getFragmentManager(), viewdetailFragment, englishGuideArrayList.get(position).getFrenchSentence(), englishGuideArrayList.get(position).getFrenchSentence());
    }
}
