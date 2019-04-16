package com.eajy.materialdesign2.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.eajy.materialdesign2.Constant;
import com.eajy.materialdesign2.R;
import com.eajy.materialdesign2.util.AppUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;


public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar_about);
        setToolbar(toolbar);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        initView();
    }

    public void initView() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        ScrollView scroll_about = findViewById(R.id.scroll_about);
        scroll_about.startAnimation(animation);

        TextView tv_card_about_2_shop = findViewById(R.id.tv_card_about_2_shop);
        TextView tv_card_about_2_email = findViewById(R.id.tv_card_about_2_email);
        TextView tv_card_about_2_git_hub = findViewById(R.id.tv_card_about_2_git_hub);
        TextView tv_card_about_2_website = findViewById(R.id.tv_card_about_2_website);
        TextView tv_card_about_source_licenses = findViewById(R.id.tv_card_about_source_licenses);
        tv_card_about_2_shop.setOnClickListener(this);
        tv_card_about_2_email.setOnClickListener(this);
        tv_card_about_2_git_hub.setOnClickListener(this);
        tv_card_about_2_website.setOnClickListener(this);
        tv_card_about_source_licenses.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab_about_share);
        fab.setOnClickListener(this);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setStartOffset(600);

        TextView tv_about_version = findViewById(R.id.tv_about_version);
        tv_about_version.setText(AppUtils.getVersionName(this));
        tv_about_version.startAnimation(alphaAnimation);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.tv_card_about_2_shop:
                intent.setData(Uri.parse(Constant.APP_URL));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.tv_card_about_2_email:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(Constant.EMAIL));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_email_intent));
                //intent.putExtra(Intent.EXTRA_TEXT, "Hi,");
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(AboutActivity.this, getString(R.string.about_not_found_email), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.tv_card_about_source_licenses:
                final Dialog dialog = new Dialog(this, R.style.DialogFullscreenWithTitle);
                dialog.setTitle(getString(R.string.about_source_licenses));
                dialog.setContentView(R.layout.dialog_source_licenses);
                WebView webView = dialog.findViewById(R.id.web_source_licenses);
                webView.loadUrl("file:///android_asset/open_source_license.html");
                MaterialButton btn_source_licenses_close = dialog.findViewById(R.id.btn_source_licenses_close);
                btn_source_licenses_close.setOnClickListener(v -> dialog.dismiss());
                dialog.show();
                break;

            case R.id.tv_card_about_2_git_hub:
                intent.setData(Uri.parse(Constant.GIT_HUB));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.tv_card_about_2_website:
                intent.setData(Uri.parse(Constant.MY_WEBSITE));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.fab_about_share:
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
                break;
        }
    }

}
