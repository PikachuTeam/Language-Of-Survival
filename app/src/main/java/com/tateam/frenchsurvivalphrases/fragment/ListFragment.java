package com.tateam.frenchsurvivalphrases.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tateam.frenchsurvivalphrases.R;
import com.tateam.frenchsurvivalphrases.app.BaseFragment;
import com.tateam.frenchsurvivalphrases.database.DataSource;
import com.tateam.frenchsurvivalphrases.entity.EnglishGuide;

import java.util.ArrayList;


public class ListFragment extends BaseFragment {
    private ListView lv;
    private ListGuideAdapter meetingAdapter;
    private ArrayList<EnglishGuide> englishGuideArrayList =new ArrayList<>();
    public static final String KEY_DETAIL="KEYDETAIL";
    public View view;
    public String inforDetail;
    public String[] inforTransfer;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_list, container, false);
        lv = (ListView) v.findViewById(R.id.lvItem);
       LoadDataGuide();
        meetingAdapter= new ListGuideAdapter(getActivity(), englishGuideArrayList);

        lv.setAdapter(meetingAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inforTransfer=new String[]{englishGuideArrayList.get(position).getEnglishSentence(),
                        englishGuideArrayList.get(position).getFrenchSentence()
                };
                Bundle bundle = new Bundle();
               // bundle.putString(KEY_DETAIL, englishGuideArrayList.get(position).getFrenchSentence());
                bundle.putStringArray(KEY_DETAIL,inforTransfer);
                DataSource.getInstance().updateRecent(englishGuideArrayList.get(position).getEnglishSentence(),englishGuideArrayList.get(position).getRecent());
                ViewdetailFragment viewdetailFragment = new ViewdetailFragment();
                viewdetailFragment.setArguments(bundle);
                //   DataSource.getInstance().updateRecent(englishGuideArrayList.get(position).getEnglishSentence());
                replaceFragment(getActivity().getSupportFragmentManager(), viewdetailFragment, englishGuideArrayList.get(position).getFrenchSentence(), englishGuideArrayList.get(position).getFrenchSentence());
            }
        });
        return v;

        //lvItem
    }
    private void LoadDataGuide() {
        DataSource.getInstance().init(getActivity().getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
        Bundle bundle = this.getArguments();
        inforDetail = bundle.getString("item");
        if(inforDetail.compareTo("meeting")==0){
            englishGuideArrayList = DataSource.getInstance().getListLesson(1);

        }
        else if (inforDetail.compareTo("restaurant")==0){
            englishGuideArrayList= DataSource.getInstance().getListLesson(2);
        }
        else if (inforDetail.compareTo("daily")==0){
            englishGuideArrayList= DataSource.getInstance().getListLesson(3);
        }

        // englishGuideArrayList = DataSource.getInstance().getListRecent(1);
    }
}
