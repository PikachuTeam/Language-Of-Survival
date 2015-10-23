package com.tateam.languageofsurvival.app;

import android.app.FragmentManager;
import android.os.Bundle;


import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import com.tateam.languageofsurvival.R;

import com.tateam.languageofsurvival.fragment.RecentFragment;
import com.tateam.languageofsurvival.utility.ShareUtil;

//import com.example.vulan.survivalguideversion3.R;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionsMenu floatingActionsMenu;
    public FloatingActionButton fbFeeback,fbRemoveAd,fbRecent;

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
           /*
                switch (item.getItemId()) {
                    case R.id.doTest:
                        currentFragment.onToolbarItemClick(DO_TEST);
                        break;
                    case R.id.selectAll:
                        currentFragment.onToolbarItemClick(SELECT_ALL);
                        break;
                    case R.id.cancelAll:
                        currentFragment.onToolbarItemClick(CANCEL_ALL);
                        break;
                }
*/
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_recent) {

           // DataSource.getInstance().
        //}

        return super.onOptionsItemSelected(item);
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
        fbRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                BaseFragment fragment = (BaseFragment) getFragmentManager().findFragmentByTag("recent");

                if (fragment == null) {
                    RecentFragment recentFragment = new RecentFragment();
                    BaseFragment.replaceFragment(getFragmentManager(), recentFragment, "recent", "recent");
                }
            }
        });

        fbFeeback = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.fab_feedback);
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

    protected void onRemoveAdClick(){

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
}
