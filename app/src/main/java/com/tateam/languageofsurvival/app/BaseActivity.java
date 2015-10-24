package com.tateam.languageofsurvival.app;

import android.app.Application;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.utility.ShareUtil;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.util.AppSpeaker;

//import com.example.vulan.survivalguideversion3.R;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private FloatingActionsMenu floatingActionsMenu;
    public FloatingActionButton fbFeeback, fbRecent;
    private TextToSpeech textToSpeech;
    private clickSpeakerListener mlisListener;
    private boolean isAdLoadingFine = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        addFragmentContent();

        findViews();
        setFabMenu();
        /*

        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.floatButton);

        floatButtonFeedback= (FloatingActionButton) findViewById(R.id.fab_feedback);
        floatButtonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // replac
            }
        });
        */
        toolbar.inflateMenu(R.menu.menu_main);
        /*
        final int abTitleId = getResources().getIdentifier("menu_main", "menu", getPackageName());
        findViewById(abTitleId).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.action_speak){
                    mlisListener.onSpeakerClick();
                }
            }
        });
        */
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.action_speak){
                    mlisListener.onSpeakerClick();
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                BaseFragment currentFragment = (BaseFragment) getFragmentManager().findFragmentById(R.id.container);
                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getFragmentManager();
        if (manager != null) {
            if (manager.getBackStackEntryCount() == 0) {
                super.onBackPressed();
            } else {
                BaseFragment currentFragment = (BaseFragment) getFragmentManager().findFragmentById(R.id.container);
                currentFragment.onBackPressed();
            }
        }
    }



    protected abstract BaseFragment getFragmentContent();

    private void addFragmentContent() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        BaseFragment fragment = getFragmentContent();
        transaction.add(R.id.container, fragment, fragment.getClass().getName());
        transaction.commit();

        /*
        * FragmentTransaction transaction=getFragmentManager.beginTransaction();
        * BaseFragment fragment=getFragmentContent;
        * transaction.add(R.id.container,fragment,fragment.getClass().getName());
        * transaction.commit();
        * */
    }

    public void setFabMenu() {
        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.floatButton);
        fbRecent = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.fab_recent);
        fbFeeback = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.fab_feedback);

        fbRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCommon.getInstance().openMoreAppDialog(BaseActivity.this);
            }
                /*
                floatingActionsMenu.collapse();
                BaseFragment fragment = (BaseFragment) getFragmentManager().findFragmentByTag("recent");

                if (fragment == null) {
                    RecentFragment recentFragment = new RecentFragment();
                    BaseFragment.replaceFragment(getFragmentManager(), recentFragment, "recent", "recent");
                }
            }
*/
        });

        fbFeeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                ShareUtil.shareToGMail(BaseActivity.this, new String[]{ShareUtil.MAIL_ADDRESS_DEFAULT}, getString(R.string.subject_mail_feedback), "");
            }
        });


    }

    protected boolean enableAdMod() {
        return false;
    }

    protected void onRemoveAdClick() {

    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public FloatingActionsMenu getMenu() {
        return floatingActionsMenu;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() ==R.id.fab_recent) {
            AppCommon.getInstance().openMoreAppDialog(this);
        } else if (view.getId() ==R.id.fab_feedback) {
            floatingActionsMenu.collapse();
            ShareUtil.shareToGMail(BaseActivity.this, new String[]{ShareUtil.MAIL_ADDRESS_DEFAULT}, getString(R.string.subject_mail_feedback), "");

        }

    }

    public interface clickSpeakerListener {
        public void onSpeakerClick();

    }

    public void setmListener(clickSpeakerListener mlisListener) {
        this.mlisListener = mlisListener;
    }


}