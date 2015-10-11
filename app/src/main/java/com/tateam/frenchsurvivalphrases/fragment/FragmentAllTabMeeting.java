package com.tateam.frenchsurvivalphrases.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.app.BaseActivity;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;
import com.tateam.frenchsurvivalphrases.entity.Page;
import com.tateam.frenchsurvivalphrases.tabs.RecentPage;
import com.tateam.frenchsurvivalphrases.tabs.SlidingTabLayout;


public class FragmentAllTabMeeting extends BaseFragment {

    //private MyPageAdapter pagerAdapter;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    //private ViewPagerAdapter pageAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected boolean enableBackButton() {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_all_tab_meeting, container, false);
       // tabLayout= (TabLayout) v.findViewById(R.id.tabcontent);
        viewPager= (ViewPager) v.findViewById(R.id.view_pager_tab);
        viewPager.setAdapter(new MyPageAdapter(getChildFragmentManager()));
        slidingTabLayout= (SlidingTabLayout) v.findViewById(R.id.slidetablayout);
         slidingTabLayout.setViewPager(viewPager);
        return v;
    }
class MyPageAdapter extends  FragmentPagerAdapter{

    String[] tabs;
    public MyPageAdapter(FragmentManager fm) {
        super(fm);
        tabs=getResources().getStringArray(R.array.tabs);
    }

    @Override
    public Fragment getItem(int position) {
        if(position%2==0){
            //replaceFragment(new  FragmentMeeting());
            FragmentMeeting fragmentMeeting=new FragmentMeeting();

            return fragmentMeeting;
        }
        else {
           // replaceFragment(new  FragmentRecent());
            FragmentRecent fragmentRecent=new FragmentRecent();
            return  fragmentRecent;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}

    }
