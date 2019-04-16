package com.eajy.materialdesign2.activity;

import android.os.Bundle;

import com.eajy.materialdesign2.R;

import androidx.appcompat.widget.Toolbar;

public class DonateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar_donate);
        setToolbar(toolbar);
    }

}
