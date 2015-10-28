package com.tateam.languageofsurvival.app;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.entity.EnglishGuide;

import java.util.ArrayList;

//import com.example.vulan.survivalguideversion3.R;
//import com.example.vulan.survivalguideversion3.entity.EnglishGuide;

public class BaseFragment extends Fragment {


    public int position = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        if (enableToolbar()) {
            getBaseActivity().getToolbar().setVisibility(View.VISIBLE);


            getBaseActivity().getSupportActionBar().setTitle(setTitle());
            getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(enableBackButton());
            getBaseActivity().getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        } else {
            getBaseActivity().getToolbar().setVisibility(View.GONE);
        }
        if (enablefloatButton()) {
            getBaseActivity().getMenu().setVisibility(View.VISIBLE);

        } else {
            getBaseActivity().getMenu().setVisibility(View.GONE);
        }
        super.onActivityCreated(savedInstanceState);
    }

    protected void onBackPressed() {
        getFragmentManager().popBackStack();
    }

    protected void onToolbarItemClick(int button) {
    }

    protected String setTitle() {
        return getString(R.string.app_name);
    }

    protected boolean enableBackButton() {
        return false;
    }

    protected boolean enableSpeak() {
        return false;
    }

    protected boolean enableToolbar() {
        return true;
    }

    protected boolean enableTitle() {
        return true;
    }

    public boolean enablefloatButton() {
        return false;
    }


    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public void replaceFragment(BaseFragment newFragment) {
        replaceFragment(newFragment, newFragment.getClass().getName(), null);
    }

    public void replaceFragment(BaseFragment newFragment, String fragmentTag, String transactionName) {
        replaceFragment(getFragmentManager(), newFragment, fragmentTag, transactionName);
    }


    public static void replaceFragment(FragmentManager fragmentManager, BaseFragment newFragment, String fragmentTag,
                                       String transactionName) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_right_enter, R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_left_enter, R.anim.fragment_slide_right_exit);
        transaction.replace(R.id.container, newFragment, fragmentTag);
        transaction.addToBackStack(transactionName);
        transaction.commit();
    }

    //get instance
    public static BaseFragment getInstance(int position) {
        BaseFragment baseFragment = new BaseFragment();
        Bundle arg = new Bundle();
        arg.putInt("position", position);
        baseFragment.setArguments(arg);
        return baseFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_speak).setVisible(enableSpeak());
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_speak:
                onSpeakPressed();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onSpeakPressed() {
        // Do smth
    }

    //Adapter
    public class ListGuideAdapter extends BaseAdapter {
        Context mContext;
        //adapter ném ra thành class ngoài
        ArrayList<EnglishGuide> englishGuideList;
        LayoutInflater inflater;


        public ListGuideAdapter(Context context, ArrayList<EnglishGuide> englishGuideList) {
            this.mContext = context;
            this.englishGuideList = englishGuideList;
            inflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return englishGuideList.size();
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
            View v = convertView;
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                v = layoutInflater.inflate(R.layout.list_guide_item, parent, false);
                mViewHolder = new MyViewHolder();
                v.setTag(mViewHolder);
            } else {
                mViewHolder = (MyViewHolder) v.getTag();
            }
            //  mViewHolder.frenchText = (TextView) v.findViewById(R.id.textFrench);

            //  mViewHolder.idText = (TextView) v.findViewById(R.id.Textid);
            //  mViewHolder.idText.setTag(englishGuideList.get(position));
            mViewHolder.englishText = (TextView) v.findViewById(R.id.textEnglish);
            mViewHolder.englishText.setTag(englishGuideList.get(position));
            Typeface myTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "the_conjuring.ttf");
            // mViewHolder.frenchText.setTypeface(myTypeFace);
            mViewHolder.englishText.setTypeface(myTypeFace);

            //mViewHolder.idText.setText(englishGuideList.get(position).getType());
            // mViewHolder.frenchText.setText(englishGuideList.get(position).getFrenchSentence());
            mViewHolder.englishText.setText(englishGuideList.get(position).getEnglishSentence());


            return v;

        }


        private class MyViewHolder {
            // TextView idText;
            //  TextView frenchText;
            TextView englishText;

        }
    }


}
