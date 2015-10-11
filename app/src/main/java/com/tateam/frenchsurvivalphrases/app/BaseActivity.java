package com.tateam.frenchsurvivalphrases.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tateam.frenchsurvivalphrases.R;

//import com.example.vulan.survivalguideversion3.R;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        addFragmentContent();

        findViews();

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
                BaseFragment currentFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
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
                BaseFragment currentFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
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
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
