package com.eajy.materialdesign2.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.eajy.materialdesign2.R;
import com.eajy.materialdesign2.adapter.RecyclerViewAdapter;
import com.eajy.materialdesign2.util.AppUtils;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BottomAppBarActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;
    private RecyclerViewAdapter adapter;
    private List<String> data;
    private BottomAppBar bottomAppBar;
    private TextView bottomAppBarTitle;
    private Chip positionChip, radiusChip, marginChip, showTitleChip;

    boolean isFabAlignRight = false;
    boolean isCutoutMarginZero = false;
    boolean isCutoutRadiusZero = false;
    boolean showBottomBarTitle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_app_bar);
        initData();
        initViews();
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("List item row: " + i);
        }
    }

    private void initViews() {
        fab = findViewById(R.id.fab_bottom_appbar);
        mRecyclerView = findViewById(R.id.recycler_view_bottom_appbar);
        bottomAppBar = findViewById(R.id.bottom_App_bar);
        positionChip = findViewById(R.id.position_chip);
        radiusChip = findViewById(R.id.radius_chip);
        marginChip = findViewById(R.id.margin_chip);
        showTitleChip = findViewById(R.id.show_title_chip);
        bottomAppBarTitle = findViewById(R.id.bottom_app_bar_title);
        setToolbar(bottomAppBar);

        if (AppUtils.getScreenWidthDp(this) >= 1200) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else if (AppUtils.getScreenWidthDp(this) >= 800) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        }

        adapter = new RecyclerViewAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.setItems(data);

        setupUiClicks();
    }

    private void setupUiClicks() {
        positionChip.setOnCheckedChangeListener((compoundButton, b) -> {
            isFabAlignRight = b;
            resetBottomAppBar();
        });
        radiusChip.setOnCheckedChangeListener((compoundButton, b) -> {
            isCutoutRadiusZero = b;
            resetBottomAppBar();
        });
        marginChip.setOnCheckedChangeListener((compoundButton, b) -> {
            isCutoutMarginZero = b;
            resetBottomAppBar();
        });
        showTitleChip.setOnCheckedChangeListener((compoundButton, b) -> {
            showBottomBarTitle = b;
            resetBottomAppBar();
        });
        fab.setOnClickListener(view -> {
            adapter.addItem(0, "1");
            mRecyclerView.smoothScrollToPosition(0);
        });
    }

    private void resetBottomAppBar() {
        bottomAppBar.setFabAlignmentMode(isFabAlignRight ? BottomAppBar.FAB_ALIGNMENT_MODE_END : BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        bottomAppBar.setFabCradleMargin(isCutoutMarginZero ? 0 : 17f); //initial default value 17f
        bottomAppBar.setFabCradleRoundedCornerRadius(isCutoutRadiusZero ? 0 : 28f); //initial default value 28f

        bottomAppBarTitle.setVisibility(showBottomBarTitle ? View.VISIBLE : View.GONE); //By Default its not suggested to add title but this is just a method if required.
        if (showBottomBarTitle)
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

        bottomAppBar.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu_main, menu);
        return true;
    }

}

