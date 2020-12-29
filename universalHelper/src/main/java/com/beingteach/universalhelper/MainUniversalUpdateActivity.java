package com.beingteach.universalhelper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainUniversalUpdateActivity extends AppCompatActivity {

    boolean isForceUpdate;
    ImageView ivCloseActivity;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_universal_update);
        ivCloseActivity = findViewById(R.id.ivCloseActivity);
        final PackageManager pm = this.getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(this.getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");

        Button btnUpdateNow = findViewById(R.id.btnUpdateNow);
        btnUpdateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" +" getPackageName()")));
                }
            }
        });

        if (getIntent() != null) {
            isForceUpdate = getIntent().getBooleanExtra("isForceUpdate", true);
            if (isForceUpdate) {
                ivCloseActivity.setVisibility(View.GONE);
            } else {
                ivCloseActivity.setVisibility(View.VISIBLE);
            }
        }


        ivCloseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ImageView ivAppIcon = findViewById(R.id.ivAppIcon);

        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
        getWindow().setTitle("");

        Drawable d = getPackageManager().getApplicationIcon(getApplicationInfo());
        ivAppIcon.setImageDrawable(d);

        TextView tvAppName = findViewById(R.id.tvAppName);
        TextView tvAppDesc = findViewById(R.id.tvAppDesc);

        tvAppName.setText(applicationName + " needs an update");
        tvAppDesc.setText("To keep using " + applicationName + ", download the latest version");

    }
}