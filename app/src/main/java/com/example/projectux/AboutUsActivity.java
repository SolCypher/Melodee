package com.example.projectux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class AboutUsActivity extends AppCompatActivity {

    private TextView greeting;

    String username;
    private ImageButton homeBtn, albumBtn, aboutBtn, offBtn;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem informationTab, mapTab;
    private PagerAdapter pagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        initializeItems();
        username = getIntent().getStringExtra("USERNAME_KEY");
        greeting.setText("Welcome, " + username);

        homeBtn = findViewById(R.id.homeBtn);
        albumBtn = findViewById(R.id.albumBtn);
        aboutBtn = findViewById(R.id.aboutBtn);
        offBtn = findViewById(R.id.offBtn);


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });
        albumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbumPage();
            }
        });
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutPage();
            }
        });
        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginPage();
            }
        });

        //Tabs
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                pagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    private void initializeItems() {

        this.greeting = findViewById(R.id.greeting);
        this.tabLayout = findViewById(R.id.tabLayout);
        this.viewPager = findViewById(R.id.viewPager);
        this.informationTab = findViewById(R.id.informationTab);
        this.mapTab = findViewById(R.id.mapTab);

    }
    private void openLoginPage() {
        Intent toLoginPage = new Intent(this, LoginActivity.class);
        startActivity(toLoginPage);
    }

    private void openAboutPage() {
        Intent toAboutPage = new Intent(this, AboutUsActivity.class);
        toAboutPage.putExtra("USERNAME_KEY", username);
        startActivity(toAboutPage);
    }

    private void openAlbumPage() {
        Intent toAlbumPage = new Intent(this, AlbumsActivity.class);
        toAlbumPage.putExtra("USERNAME_KEY", username);
        startActivity(toAlbumPage);
    }


    private void openHomePage() {
        Intent toHomePage = new Intent(this, HomeActivity.class);
        toHomePage.putExtra("USERNAME_KEY", username);
        startActivity(toHomePage);

    }
}