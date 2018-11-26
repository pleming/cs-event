package com.event.cs.csevent.service;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.event.cs.csevent.R;

public class CustomProgressBar extends Application {
    private static CustomProgressBar customProgressBar;
    private AppCompatDialog progressDialog;

    public static CustomProgressBar getInstance() {
        if (customProgressBar == null)
            customProgressBar = new CustomProgressBar();

        return customProgressBar;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void progressON(Activity activity, String message) {
        if (activity == null || activity.isFinishing()) {
            return;
        }

        if (progressDialog != null && progressDialog.isShowing()) {
            progressSET(message);
        } else {
            progressDialog = new AppCompatDialog(activity);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.progress_loading);
            progressDialog.show();
        }

        final ImageView img_loading_frame = (ImageView) progressDialog.findViewById(R.id.iv_frame_loading);
        final AnimationDrawable frameAnimation = (AnimationDrawable) img_loading_frame.getBackground();
        img_loading_frame.post(new Runnable() {
            @Override
            public void run() {
                frameAnimation.start();
            }
        });

        TextView tv_progress_message = (TextView) progressDialog.findViewById(R.id.progress_message);
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.setText(message);
        }
    }

    public void progressSET(String message) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }

        TextView tv_progress_message = (TextView) progressDialog.findViewById(R.id.progress_message);

        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.setText(message);
        }
    }

    public void progressOFF() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
