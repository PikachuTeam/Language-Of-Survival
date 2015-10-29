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


public class RecentFragment extends BaseFragment implements GuideAdapter.clickListener {

    private ListView lv;
    private ListGuideAdapter meetingAdapter;
    private ArrayList<EnglishGuide> englishGuideArrayList = new ArrayList<>();
    public static final String KEY_DETAIL = "KEYDETAIL";
    public View view;
    public int position = 2;
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_english_recent);
        layoutManager = new LinearLayoutManager(getBaseActivity());
        recyclerView.setLayoutManager(layoutManager);

        LoadDataGuide();

        guideAdapter = new GuideAdapter(getBaseActivity(), englishGuideArrayList);
        recyclerView.setAdapter(guideAdapter);
        guideAdapter.setmListener(this);


        return view;
    }

    private void LoadDataGuide() {
        DataSource.getInstance().initIfNeeded(getActivity().getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
        englishGuideArrayList = DataSource.getInstance().getListRecent();
    }

    @Override
    public void onPhraseClick(int position) {
        Bundle bundle = new Bundle();
        inforTransfer = new String[]{englishGuideArrayList.get(position).getEnglishSentence(),
                englishGuideArrayList.get(position).getFrenchSentence()
        };
        bundle.putStringArray(KEY_DETAIL, inforTransfer);
        ViewdetailFragment viewdetailFragment = new ViewdetailFragment();
        viewdetailFragment.setArguments(bundle);

        replaceFragment(getActivity().getFragmentManager(), viewdetailFragment, englishGuideArrayList.get(position).getFrenchSentence(), englishGuideArrayList.get(position).getFrenchSentence());
    }
}
