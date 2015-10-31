package com.tateam.languageofsurvival.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.tateam.languageofsurvival.R;
import com.tateam.languageofsurvival.utility.ShareUtil;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.ads.AdsSmallBannerHandler;
import tatteam.com.app_common.util.CloseAppHandler;

//import com.example.vulan.survivalguideversion3.R;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, CloseAppHandler.OnCloseAppListener {
    private static final boolean ADS_ENABLE = true;

    public FloatingActionButton fbFeedback, fbRecent;
    private Toolbar toolbar;
    private FloatingActionsMenu floatingActionsMenu;
    private RelativeLayout fabListen;
    private CloseAppHandler closeAppHandler;
    private AdsSmallBannerHandler adsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        addFragmentContent();
        findViews();
        setFabMenu();
        setSupportActionBar(toolbar);
        closeAppHandler = new CloseAppHandler(this);
        closeAppHandler.setListener(this);

        if(ADS_ENABLE) {
            FrameLayout adsContainer = (FrameLayout) findViewById(R.id.ads_container);
            adsHandler = new AdsSmallBannerHandler(this, adsContainer);
            adsHandler.setup();
        }
    }

    @Override
    public void onBackPressed() {
        if (floatingActionsMenu.isExpanded()) {
            floatingActionsMenu.collapse();
            return;
        }
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            if (fragmentManager.getBackStackEntryCount() <= 0) {
                //  super.onBackPressed();
                closeAppHandler.handlerKeyBack(this);

            } else {
                BaseFragment currentFragment = (BaseFragment) fragmentManager.findFragmentById(R.id.container);
                currentFragment.onBackPressed();
            }
        }
    }

    protected abstract BaseFragment getFragmentContent();

    private void addFragmentContent() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            BaseFragment fragment = getFragmentContent();
            transaction.add(R.id.container, fragment, fragment.getClass().getName());
            transaction.commit();
        } else {
            fragmentManager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void setFabMenu() {
        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.floatButton);
        fbRecent = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.fab_recent);
        fbFeedback = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.fab_feedback);

        fbRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                AppCommon.getInstance().openMoreAppDialog(BaseActivity.this);
            }

        });

        fbFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                ShareUtil.shareToGMail(BaseActivity.this, new String[]{ShareUtil.MAIL_ADDRESS_DEFAULT}, getString(R.string.subject_mail_feedback), "");
            }
        });
        fabListen = (RelativeLayout) findViewById(R.id.fablisten);
        floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                fabListen.setVisibility(View.VISIBLE);
            }

            @Override
            public void onMenuCollapsed() {
                fabListen.setVisibility(View.INVISIBLE);
            }
        });
        fabListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
            }
        });
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
        if (view.getId() == R.id.fab_recent) {
            AppCommon.getInstance().openMoreAppDialog(this);
        } else if (view.getId() == R.id.fab_feedback) {
            floatingActionsMenu.collapse();
            ShareUtil.shareToGMail(BaseActivity.this, new String[]{ShareUtil.MAIL_ADDRESS_DEFAULT}, getString(R.string.subject_mail_feedback), "");

        }
    }


    @Override
    public void onRateAppDialogClose() {
        finish();
    }

    @Override
    public void onTryToCloseApp() {
        Toast.makeText(this, R.string.please_click_back_again_to_exit, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onReallyWantToCloseApp() {
        finish();
    }


}
