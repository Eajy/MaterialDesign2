package com.eajy.materialdesign2.activity;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.eajy.materialdesign2.R;
import com.eajy.materialdesign2.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class BottomNavigationActivity extends BaseActivity {

    private ViewPager viewPager;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        initView();
    }

    private void initView() {
        setStatusBarColor(getResources().getColor(R.color.transparent));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setToolbar(toolbar);

        View view1 = getLayoutInflater().inflate(R.layout.item_view_pager, null);
        View view2 = getLayoutInflater().inflate(R.layout.item_view_pager, null);
        View view3 = getLayoutInflater().inflate(R.layout.item_view_pager, null);
        View view4 = getLayoutInflater().inflate(R.layout.item_view_pager, null);
        List<View> viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewPager = findViewById(R.id.view_pager_bottom_navigation);
        ViewPagerAdapter adapter = new ViewPagerAdapter(viewList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pageChangeListener);

        navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            ArgbEvaluator evaluator = new ArgbEvaluator();
            int evaluate = getResources().getColor(R.color.app_blue);
            if (position == 0) {
                evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.app_blue), getResources().getColor(R.color.app_green));
            } else if (position == 1) {
                evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.app_green), getResources().getColor(R.color.app_yellow));
            } else if (position == 2) {
                evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.app_yellow), getResources().getColor(R.color.app_red));
            } else {
                evaluate = getResources().getColor(R.color.app_red);
            }
            ((View) viewPager.getParent()).setBackgroundColor(evaluate);
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    navigation.setSelectedItemId(R.id.bottom_navigation_blue);
                    break;
                case 1:
                    navigation.setSelectedItemId(R.id.bottom_navigation_green);
                    break;
                case 2:
                    navigation.setSelectedItemId(R.id.bottom_navigation_yellow);
                    break;
                case 3:
                    navigation.setSelectedItemId(R.id.bottom_navigation_red);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_navigation_blue:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.bottom_navigation_green:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.bottom_navigation_yellow:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.bottom_navigation_red:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

}

