package com.eajy.materialdesign2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.eajy.materialdesign2.R;

import androidx.annotation.Nullable;

/**
 * Created by zhangxiao on 2019/4/3
 */
public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void onResume() {
        countDownTimer.cancel();
        countDownTimer.start();
        super.onResume();
    }

    @Override
    protected void onStop() {
        countDownTimer.cancel();
        super.onStop();
    }

    CountDownTimer countDownTimer = new CountDownTimer(600, 600) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(-1, R.anim.activity_exit_alpha);
            finish();
        }
    };
}
