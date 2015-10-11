package com.tateam.frenchsurvivalphrases.app;



//import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.example.vulan.survivalguideversion3.R;
//import com.example.vulan.survivalguideversion3.entity.EnglishGuide;

import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.entity.EnglishGuide;

import java.util.ArrayList;

public class BaseFragment extends Fragment {


    public int position=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        if (enableToolbar()) {
            getBaseActivity().getToolbar().setVisibility(View.VISIBLE);
            getBaseActivity().getToolbar().setTitle(setTitle());

            if (enableBackButton()) {
                getBaseActivity().getToolbar().setNavigationIcon(R.drawable.ic_chevron_left);
            } else {
                getBaseActivity().getToolbar().setNavigationIcon(null);
            }
          /*
            getBaseActivity().getToolbar().getMenu().findItem(R.id.doTest).setVisible(enableButtonDoTest());
            getBaseActivity().getToolbar().getMenu().findItem(R.id.selectAll).setVisible(enableButtonSelectAll());
            getBaseActivity().getToolbar().getMenu().findItem(R.id.cancelAll).setVisible(enableButtonCancelAll());
            */

        } else {
            getBaseActivity().getToolbar().setVisibility(View.GONE);
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

    protected boolean enableToolbar() {
        return true;
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



    public static void replaceFragment(FragmentManager manager, BaseFragment newFragment, String fragmentTag,
                                       String transactionName) {
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
       // transaction.setCustomAnimations(R.anim.fragment_slide_right_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_left_enter, R.anim.fragment_slide_right_exit);
        transaction.replace(R.id.container, newFragment, fragmentTag);
        transaction.addToBackStack(transactionName);
        transaction.commit();
    }

    //get instance
    public static BaseFragment getInstance(int position){
        BaseFragment baseFragment=new BaseFragment();
        Bundle arg=new Bundle();
        arg.putInt("position",position);
        baseFragment.setArguments(arg);
        return baseFragment;
    }

    //Adapter
    public class ListGuideAdapter extends BaseAdapter {
        Context mContext;
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
