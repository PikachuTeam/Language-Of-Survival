package com.tateam.frenchsurvivalphrases.tabs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.app.BaseActivity;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;
import com.tateam.frenchsurvivalphrases.database.DataSource;
import com.tateam.frenchsurvivalphrases.entity.EnglishGuide;
import com.tateam.frenchsurvivalphrases.entity.Page;
import com.tateam.frenchsurvivalphrases.fragment.ViewdetailFragment;

import java.util.ArrayList;
import java.util.List;



public class RecentPage extends Page implements RecentAdapter.ClickListener {
    private static int MAX_COUNT = 100;

    private RecyclerView mRecyclerView;
    private RecentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout noRecent;
    private List<EnglishGuide> list;


    public RecentPage(BaseActivity activity) {
        super(activity);
        noRecent = (RelativeLayout) content.findViewById(R.id.non_recent);

        mRecyclerView = (RecyclerView) content.findViewById(R.id.recycler_view_list_idioms);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_recent;
    }

    public void refreshData() {
        list = new ArrayList<>();
        list = DataSource.getInstance().getListRecent();
        if (list.isEmpty()) {
            noRecent.setVisibility(View.VISIBLE);
        } else {
            noRecent.setVisibility(View.GONE);
        }
        mAdapter = new RecentAdapter(list);
        mAdapter.setMlisListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void ItemClicked(int position) {
        ViewdetailFragment detailIdiomFragment = new ViewdetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("KEYDETAIL", list.get(position).getEnglishSentence());
        detailIdiomFragment.setArguments(bundle);
        BaseFragment.replaceFragment(activity.getFragmentManager(), detailIdiomFragment, list.get(position).getEnglishSentence(),
                list.get(position).getEnglishSentence());
    }



    @Override
    public void destroy() {
        list = null;
        mAdapter = null;
        super.destroy();
    }
}
