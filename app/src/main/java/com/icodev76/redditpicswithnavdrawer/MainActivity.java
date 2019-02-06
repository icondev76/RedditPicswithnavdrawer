package com.icodev76.redditpicswithnavdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.icodev76.redditpicswithnavdrawer.fragment.fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        //int id = menuItem.getItemId();
        Fragment myFragment = null;
        Class fragmentClass = null;
        fragmentClass = fragment.class;
        Bundle bundle = new Bundle();

        /*switch (menuItem.getItemId()) {
            case R.id.a:
                bundle.putString("options", "AsianguysNSFW");
                break;
            case R.id.b:
                bundle.putString("options", "broslikeus");
                break;
            case R.id.c:
                bundle.putString("options", "gayporndepot");
                break;
            case R.id.d:
                bundle.putString("options", "GayGifs");
                break;
            case R.id.e:
                bundle.putString("options", "Bisexy");
                break;
            case R.id.f:
                bundle.putString("options", "CuteGuyButts");
                break;
            default:
                bundle.putString("options", "AsianguysNSFW");
        }*/

        /*if (id == R.id.a) {
            // Handle the camera action
        } else if (id == R.id.b) {

        } else if (id == R.id.c) {

        } else if (id == R.id.d) {

        } else if (id == R.id.e) {

        } else if (id == R.id.f) {

        }*/
                try {
                    myFragment = (fragment) fragmentClass.newInstance();
                    myFragment.setArguments(bundle);

                } catch (Exception e) {
                    e.getStackTrace();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.framelayout, myFragment).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                String title= (String) menuItem.getTitle();
                bundle.putString("options", title);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
        }






}
