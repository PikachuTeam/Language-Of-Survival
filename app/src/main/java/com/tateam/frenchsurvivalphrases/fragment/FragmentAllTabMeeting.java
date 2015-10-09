package com.tateam.frenchsurvivalphrases.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;


public class FragmentAllTabMeeting extends BaseFragment {

    //private MyPageAdapter pagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //private ViewPagerAdapter pageAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_all_tab_meeting, container, false);
       // tabLayout= (TabLayout) v.findViewById(R.id.tabcontent);
        viewPager= (ViewPager) v.findViewById(R.id.view_pager_tab);
        return v;
    }
    private void setupViewPager(ViewPager viewPager) {
        /*
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.accent_material_light)), "CAT");
        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.ripple_material_light)), "DOG");
        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark)), "MOUSE");
        viewPager.setAdapter(adapter);
        */
    }
    /*
    private static class MyPageAdapter extends PagerAdapter {

       // private BaseActivity activity;

        public BaseFragment[] myPages;

        public MyPageAdapter(BaseActivity activity) {
            this.activity = activity;
            myPages = new Page[]{new ConceptsPage(activity), new AlphabetPage(activity), new RecentPage(activity)};
        }

        @Override
        public int getCount() {
            return myPages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View layout = myPages[position].getContent();
            container.addView(layout);
            if (position == 2) {
                RecentPage recentPage = (RecentPage) myPages[2];
                recentPage.refreshData();
            }
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            Page page = myPages[position];
            page.destroy();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) return activity.getString(R.string.tab1);
            else if (position == 1) return activity.getString(R.string.tab2);
            return activity.getString(R.string.tab3);
        }


    }

*/
    }
