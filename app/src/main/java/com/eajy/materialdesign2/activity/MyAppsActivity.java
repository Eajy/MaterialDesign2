package com.eajy.materialdesign2.activity;

import android.os.Bundle;

import com.eajy.materialdesign2.Data;
import com.eajy.materialdesign2.R;
import com.eajy.materialdesign2.adapter.MyAppsAdapter;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyAppsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_apps);

        Toolbar toolbar = findViewById(R.id.toolbar_my_apps);
        setToolbar(toolbar);

        RecyclerView recycler_my_apps = findViewById(R.id.recycler_my_apps);
        MyAppsAdapter adapter = new MyAppsAdapter(this, Data.getMyAppsData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_my_apps.setLayoutManager(linearLayoutManager);
        recycler_my_apps.setAdapter(adapter);
    }

}
