package com.example.tabappviewpager2;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout = findViewById(R.id.tabBar);
        final ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        setupViewPager2(viewPager2, tabLayout);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 1) {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                R.color.colorAccent));
                    }
                } else if (tab.getPosition() == 2) {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                android.R.color.darker_gray));
                    }
                } else {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                R.color.colorPrimaryDark));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private ViewPager2Adapter setupViewPager2(ViewPager2 viewPager2, TabLayout tabLayout) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        Map<String, Object> redMap = setTabTitleAndFragment("Red", new RedFragment());
        Map<String, Object> blueMap = setTabTitleAndFragment("Blue", new BlueFragment());
        Map<String, Object> greenMap = setTabTitleAndFragment("Green", new GreenFragment());

        mapList.add(redMap);
        mapList.add(blueMap);
        mapList.add(greenMap);

        final ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this, mapList);
        viewPager2.setAdapter(viewPager2Adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(viewPager2Adapter.getTitle(position));
            }
        }).attach();
        return viewPager2Adapter;
    }

    Map<String, Object> setTabTitleAndFragment(String title, Fragment fragment){

        Map<String, Object> fragmentWithTitleMap = new HashMap<>();

        fragmentWithTitleMap.put("fragmentTitle", title);
        fragmentWithTitleMap.put("fragment", fragment);

        return fragmentWithTitleMap;
    }
}