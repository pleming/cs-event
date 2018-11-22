package com.event.cs.csevent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.event.cs.csevent.callback.ProductLoadCallback;
import com.event.cs.csevent.model.ProductItem;
import com.event.cs.csevent.service.ProductService;
import com.event.cs.csevent.tab.SectionsPagerAdapter;
import com.event.cs.csevent.util.AjaxManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private ProductService productService;
    private Integer csType = 1;
    private String searchTxt = null;

    private void changeEventType(final int tabPosition) {
        ArrayList<ProductItem> productInfo = null;

        if (tabPosition == 0) {
            productInfo = mSectionsPagerAdapter.getOneToOneFragment().getProductInfo();
        } else if (tabPosition == 1) {
            productInfo = mSectionsPagerAdapter.getTwoToOneFragment().getProductInfo();
        } else if (tabPosition == 2) {
            productInfo = mSectionsPagerAdapter.getThreeToOneFragment().getProductInfo();
        }

        productInfo.clear();

        productService.loadProduct(csType, tabPosition + 1, searchTxt, productInfo, new ProductLoadCallback() {
            @Override
            public void callback() {
                mSectionsPagerAdapter.notifyDataSetChanged();
                mViewPager.setCurrentItem(tabPosition);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AjaxManager.init(getApplicationContext());

        /* Toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /* Navigation Drawer */
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /* Tab Layout */
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), getApplicationContext());

        /* View Pager */
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeEventType(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(mViewPager);

        /* Initialization */
        productService = new ProductService(getApplication());
        csType = 1;
        changeEventType(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_cu) {
            csType = 1;
        } else if (id == R.id.nav_gs25) {
            csType = 2;
        } else if (id == R.id.nav_7_eleven) {
            csType = 3;
        }

        searchTxt = null;
        changeEventType(0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTxt = query;
                changeEventType(0);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchTxt = null;
                changeEventType(0);
                return false;
            }
        });

        return true;
    }
}
