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


public class FragmentRestaurant extends BaseFragment {
    private ListView lv;
    private ListGuideAdapter meetingAdapter;
    private ArrayList<EnglishGuide> englishGuideArrayList =new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected boolean enableBackButton() {
        return true;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting, container, false);

        lv = (ListView) view.findViewById(R.id.lvMeeting);
        LoadDataGuide();
        meetingAdapter= new ListGuideAdapter(getActivity(), englishGuideArrayList);
        lv.setAdapter(meetingAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Intent intent=new Intent(this,getBaseActivity().replace);
                Bundle bundle = new Bundle();
                bundle.putString(FragmentMeeting.KEY_DETAIL, englishGuideArrayList.get(position).getFrenchSentence());
                FragmentViewdetail fragmentViewdetail = new FragmentViewdetail();
                fragmentViewdetail.setArguments(bundle);

                replaceFragment(getActivity().getSupportFragmentManager(), fragmentViewdetail, englishGuideArrayList.get(position).getFrenchSentence(), englishGuideArrayList.get(position).getFrenchSentence());
            }
        });
        return view;

    }
    private void LoadDataGuide() {
        DataSource.getInstance().init(getActivity().getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
        englishGuideArrayList = DataSource.getInstance().getListLesson(2);
    }
    /*
    public class ListMeetingAdapter extends BaseAdapter {
        Context mContext;
        ArrayList<EnglishGuide> guideList;
        LayoutInflater inflater;


        public ListMeetingAdapter(Context context, ArrayList<EnglishGuide> guideList) {
            this.mContext = context;
            this.guideList = guideList;
            inflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return guideList.size();
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
            mViewHolder.frenchText = (TextView) v.findViewById(R.id.textFrench);
            mViewHolder.englishText = (TextView) v.findViewById(R.id.textEnglish);
            mViewHolder.idText = (TextView) v.findViewById(R.id.Textid);
            mViewHolder.idText.setTag(guideList.get(position));
            Typeface myTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "the_conjuring.ttf");
            mViewHolder.frenchText.setTypeface(myTypeFace);
            mViewHolder.englishText.setTypeface(myTypeFace);

            //mViewHolder.idText.setText(guideList.get(position).getType());
            mViewHolder.frenchText.setText(guideList.get(position).getFrenchSentence());
            mViewHolder.englishText.setText(guideList.get(position).getEnglishSentence());


            return v;

        }


        private class MyViewHolder {
            TextView idText;
            TextView frenchText;
            TextView englishText;

        }
    }
*/

}
