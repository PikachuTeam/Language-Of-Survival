  package com.tateam.frenchsurvivalphrases.fragment;

  import android.os.Bundle;

  import android.support.design.widget.FloatingActionButton;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.AdapterView;
  import android.widget.ListView;


  import com.tateam.frenchsurvivalphrases.R;
  import com.tateam.frenchsurvivalphrases.app.BaseActivity;
  import com.tateam.frenchsurvivalphrases.app.BaseFragment;
  import com.tateam.frenchsurvivalphrases.database.DataSource;
  import com.tateam.frenchsurvivalphrases.entity.EnglishGuide;
  import com.tateam.frenchsurvivalphrases.utility.FragmentWebView;
  import com.tateam.frenchsurvivalphrases.utility.ShareUtil;

  import android.support.v4.app.FragmentManager;
  import java.util.ArrayList;


  public class FragmentMeeting extends BaseFragment {
      private ListView lv;
      private ListGuideAdapter meetingAdapter;
      private ArrayList<EnglishGuide> englishGuideArrayList =new ArrayList<>();
      public static final String KEY_DETAIL="KEYDETAIL";
      public View view;
      public int position=1;
      private FloatingActionButton floatingActionButton;
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
         view = inflater.inflate(R.layout.fragment_meeting, container, false);

        floatingActionButton= (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.shareToGMail(getActivity(), new String[]{ShareUtil.MAIL_ADDRESS_DEFAULT}, getString(R.string.subject_mail_feedback), "");
            }
        });

        lv = (ListView) view.findViewById(R.id.lvMeeting);
        LoadDataGuide();
        meetingAdapter= new ListGuideAdapter(getActivity(), englishGuideArrayList);

        lv.setAdapter(meetingAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Intent intent=new Intent(this,getBaseActivity().replace);
                Bundle bundle = new Bundle();
                bundle.putString(KEY_DETAIL, englishGuideArrayList.get(position).getFrenchSentence());
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
          englishGuideArrayList = DataSource.getInstance().getListLesson(1);
         // englishGuideArrayList = DataSource.getInstance().getListRecent(1);
      }

     private void LoadDataRecent(){
         DataSource.getInstance().init(getActivity().getApplicationContext());
         DataSource.getInstance().createDatabaseIfNeed();
         englishGuideArrayList = DataSource.getInstance().getListRecent(1);
     }
      /*
      public View getContent() {
          return view;
      }
      */

}
