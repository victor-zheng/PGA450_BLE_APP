package com.example.zhengquanfa.pga450bleapp;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MainActivityFragment fragment1 = new MainActivityFragment();
        transaction.add(R.id.main, fragment1,"frag1");
        transaction.commit();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
// App Logo
//        toolbar.setLogo(R.mipmap.radar);
// Title
        toolbar.setTitle(R.string.app_name);
// Sub Title
//        toolbar.setSubtitle("Sub title");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
            switch (id) {
                case R.id.action_scan:
                    FragmentManager manager1 = getSupportFragmentManager();
                    FragmentTransaction transaction1 = manager1.beginTransaction();
                    MainActivityFragment fragment1 = new MainActivityFragment();
                    transaction1.replace(R.id.main, fragment1);
                    transaction1.commit();
                    break;
                case R.id.action_run:
                    FragmentManager manager2 = getSupportFragmentManager();
                    FragmentTransaction transaction2 = manager2.beginTransaction();
                    FragmentMeasure fragment2 = new FragmentMeasure();
                    transaction2.replace(R.id.main, fragment2);
                    transaction2.commit();
                    break;
                case R.id.action_exit:
//                    ActivityManager am= (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
//                    am.killBackgroundProcesses(this.getPackageName());
                    System.exit(0);
                    break;
            }
        return super.onOptionsItemSelected(item);
    }
}
