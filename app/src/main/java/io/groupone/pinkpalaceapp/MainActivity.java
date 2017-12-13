package io.groupone.pinkpalaceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create Navigation drawer and inflate layout
        NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);

// Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

// Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();

                        // Define Action for each navigation item
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home: //home
                                Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(homeIntent);
                                return true;
                            case R.id.nav_gallery: //speed gallery
                                Intent galleryIntent = new Intent(MainActivity.this, SpeedGallery.class);
                                startActivity(galleryIntent);
                                return true;
                            case R.id.nav_feed: //feed
                                Intent feedIntent = new Intent(MainActivity.this, FeedActivity.class);
                                startActivity(feedIntent);
                                return true;
                            case R.id.nav_login: //login
                                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(loginIntent);
                                return true;
                            case R.id.nav_join://join
                                Intent joinIntent = new Intent(MainActivity.this, JoinActivity.class);
                                startActivity(joinIntent);
                                return true;

                            case R.id.nav_contact: //contact
                                Intent contactIntent = new Intent(MainActivity.this,Contact.class);
                                startActivity(contactIntent);
                                return true;

                            case R.id.nav_donate: //donate
                                Intent donateIntent = new Intent(MainActivity.this, Donate.class);
                                startActivity(donateIntent);
                                return true;

                            default:
                                MainActivity home = new MainActivity();
                                return true;
                        }
                    }
                });

        //create viewpager container for tab contents
        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        // Create toolbar tabs
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    // Add Fragments to Tabs
    /*
    * Setup found via: https://codelabs.developers.google.com/codelabs/material-design-style/index.html?index=..%2F..%2Fio2016#3
    * */
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ExhibitsFragment(), "Exhibits");
        adapter.addFragment(new MapsFragment(), "Maps");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
