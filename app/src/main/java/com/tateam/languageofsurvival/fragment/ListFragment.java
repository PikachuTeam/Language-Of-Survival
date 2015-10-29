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
    private ArrayList<EnglishGuide> englishGuideArrayList = new ArrayList<>();
    public static final String KEY_DETAIL = "KEYDETAIL";
    public View view;
    public String inforDetail;
    public String[] inforTransfer;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private GuideAdapter guideAdapter;
    private String title;

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

    public boolean enableTitle() {
        return false;
    }

    @Override
    protected boolean enableSpeak() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list, container, false);
        // getBaseActivity().getToolbar().setTitle("");
//        getBaseActivity().getToolbar().findViewById(R.id.action_speak).setVisibility(View.INVISIBLE);
        meetingAdapter = new ListGuideAdapter(getActivity(), englishGuideArrayList);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_english);
        layoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerView.setLayoutManager(layoutManager);
        LoadDataGuide();
        recyclerView.setAdapter(guideAdapter);
        guideAdapter.setmListener(this);
        return v;
    }

    private void LoadDataGuide() {
        DataSource.getInstance().initIfNeeded(getActivity().getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
        Bundle bundle = this.getArguments();
        inforDetail = bundle.getString("item");

        switch (inforDetail) {
            case "meeting":
                englishGuideArrayList = DataSource.getInstance().getListLesson(1);
                guideAdapter = new GuideAdapter(getBaseActivity(), englishGuideArrayList, 1);
                break;
            case "restaurant":
                englishGuideArrayList = DataSource.getInstance().getListLesson(2);
                guideAdapter = new GuideAdapter(getBaseActivity(), englishGuideArrayList, 2);
                break;
            case "daily":
                englishGuideArrayList = DataSource.getInstance().getListLesson(3);
                guideAdapter = new GuideAdapter(getBaseActivity(), englishGuideArrayList, 3);
                break;
            case "socializing":
                englishGuideArrayList = DataSource.getInstance().getListLesson(4);
                guideAdapter = new GuideAdapter(getBaseActivity(), englishGuideArrayList, 4);
                break;
            case "shopping":
                englishGuideArrayList = DataSource.getInstance().getListLesson(5);
                guideAdapter = new GuideAdapter(getBaseActivity(), englishGuideArrayList, 5);
                break;
            case "hotel":
                englishGuideArrayList = DataSource.getInstance().getListLesson(6);
                guideAdapter = new GuideAdapter(getBaseActivity(), englishGuideArrayList, 6);
                break;
            case "direction":
                englishGuideArrayList = DataSource.getInstance().getListLesson(7);
                guideAdapter = new GuideAdapter(getBaseActivity(), englishGuideArrayList, 7);
                break;
            case "weather":
                englishGuideArrayList = DataSource.getInstance().getListLesson(8);
                guideAdapter = new GuideAdapter(getBaseActivity(), englishGuideArrayList, 8);
                break;
        }

    }

    @Override
    protected String setTitle() {
        switch (inforDetail) {
            case "meeting":
                title = "Meeting";
                break;
            case "restaurant":
                title = "Restaurant";
                break;
            case "daily":
                title = "Daily";
                break;
            case "socializing":
                title = "Socializing";
                break;
            case "shopping":
                title = "Shopping";
                break;
            case "hotel":
                title = "Hotel";
                break;
            case "direction":
                title = "Direction";
                break;
            case "weather":
                title = "Weather";
                break;
        }
        return title;
    }

    @Override
    public void onPhraseClick(int position) {
        inforTransfer = new String[]{englishGuideArrayList.get(position).getEnglishSentence(),
                englishGuideArrayList.get(position).getFrenchSentence(),
        };
        int i = DataSource.getInstance().updateRecenthere(englishGuideArrayList.get(position).getEnglishSentence(), englishGuideArrayList.get(position).getRecent());
        Bundle bundle = new Bundle();
        bundle.putStringArray(KEY_DETAIL, inforTransfer);
        bundle.putString("Title", title);

        ViewdetailFragment viewdetailFragment = new ViewdetailFragment();
        viewdetailFragment.setArguments(bundle);
        replaceFragment(getActivity().getFragmentManager(), viewdetailFragment, englishGuideArrayList.get(position).getFrenchSentence(), englishGuideArrayList.get(position).getFrenchSentence());
    }
}
