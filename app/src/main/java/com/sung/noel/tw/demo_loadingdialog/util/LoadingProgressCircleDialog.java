package com.sung.noel.tw.demo_loadingdialog.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;

import com.sung.noel.tw.demo_loadingdialog.R;
import com.sung.noel.tw.demo_loadingdialog.util.implement.OnUpdateCompleteListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by noel on 2018/7/16.
 */

public class LoadingProgressCircleDialog extends Dialog {

    private ProgressBar progressBar;
    private OnUpdateCompleteListener onUpdateCompleteListener;

    public LoadingProgressCircleDialog(@NonNull Context context, int max) {
        super(context);
        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        setContentView(progressBar);
        progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.layer_list_circle_loading));
        progressBar.setMax(max);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    //----------

    /***
     *  更新進度條
     * @param progress
     */
    public void updateProgress(int progress) {
        progressBar.setProgress(progress);
        if (progress == progressBar.getMax() && onUpdateCompleteListener != null) {
            onUpdateCompleteListener.onUpdateCompleted();
        }
    }

    //------
    public void setOnUpdateCompleteListener(OnUpdateCompleteListener onUpdateCompleteListener) {
        this.onUpdateCompleteListener = onUpdateCompleteListener;
    }
}
